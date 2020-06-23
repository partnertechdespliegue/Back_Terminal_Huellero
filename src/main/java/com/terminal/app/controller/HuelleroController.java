package com.terminal.app.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.terminal.app.model.Asistencia;
import com.terminal.app.model.ResponseWrapper;
import com.terminal.app.util.BackCentral;
import com.terminal.app.util.Constantes;
import com.zkteco.biometric.FingerprintSensorErrorCode;
import com.zkteco.biometric.FingerprintSensorEx;

@RestController
@RequestMapping("/huella")
public class HuelleroController {

	int fpWidth = 0;
	// la altura de la imagen de la huella digital
	int fpHeight = 0;
	// para verificar la prueba
	private byte[] lastRegTemp = new byte[2048];
	// la longitud de lastRegTemp
	private int cbRegTemp = 0;
	// pre-register template
	private byte[][] regtemparray = new byte[3][2048];
	// registrarse
	private boolean bRegister = false;
	// identificar
	private boolean bIdentify = true;
	// identificacion de la huella
	private int iFid = 1;

	private int nFakeFunOn = 1;
	// debe ser 3
	static final int enroll_cnt = 3;
	// the index of pre-register function
	private int enroll_idx = 0;

	private byte[] imgbuf = null;
	private byte[] template = new byte[2048];
	private int[] templateLen = new int[1];

	private boolean mbStop = true;
	private long mhDevice = 0;
	private long mhDB = 0;
	private WorkThread workThread = null;

	@GetMapping("/open")
	public ResponseWrapper open() {
		ResponseWrapper resp = new ResponseWrapper();
		System.out.println("open ");
		// TODO: Si no posee el huellero, comente las líneas 58 al 62 /* */
		/*Asistencia asistencia = new Asistencia();
		asistencia.setIdAsistencia(1);
		BackCentral backCentral = new BackCentral();
		backCentral.marcarAsistencia(asistencia);
		btnOpenActionPerformed();*/
		resp.setEstado(Constantes.valTransaccionOk);
		resp.setMsg("Open");
		return resp;
	}

	@PostMapping("/enroll")
	public ResponseWrapper enroll() {
		System.out.println("enroll");
		btnEnrollActionPerformed();
		return null;
	}

	@PostMapping("/identify")
	public ResponseWrapper identify() {
		System.out.println("identify");
		btnIdentifyActionPerformed();
		return null;
	}

	@PostMapping("/registerByImage")
	public ResponseWrapper registerByImage() {
		System.out.println("registerByImage");
		btnRegImgActionPerformed();
		return null;
	}

	@PostMapping("/verifyByImage")
	public ResponseWrapper verifyByImage() {
		System.out.println("verifyByImage");
		btnVerImgActionPerformed();
		return null;
	}
	
	@PostMapping("/close")
	public ResponseWrapper close() {
		System.out.println("Close");
		btnCloseActionPerformed();
		return null;
	}

	private void btnOpenActionPerformed() {// GEN-FIRST:event_btnOpenActionPerformed
		if (0 != mhDevice) {
			// already inited
			System.out.println("Please close device first!\n");
			return;
		}
		int ret = FingerprintSensorErrorCode.ZKFP_ERR_OK;
		// Initialize
		cbRegTemp = 0;
		bRegister = false;
		bIdentify = false;
		iFid = 1;
		enroll_idx = 0;
		if (FingerprintSensorErrorCode.ZKFP_ERR_OK != FingerprintSensorEx.Init()) {
			System.out.println("Init failed!\n");
			return;
		}
		ret = FingerprintSensorEx.GetDeviceCount();
		if (ret < 0) {
			System.out.println("No devices connected!\n");
			FreeSensor();
			return;
		}
		if (0 == (mhDevice = FingerprintSensorEx.OpenDevice(0))) {
			System.out.println("Open device fail, ret = " + ret + "!\n");
			FreeSensor();
			return;
		}
		if (0 == (mhDB = FingerprintSensorEx.DBInit())) {
			System.out.println("Init DB fail, ret = " + ret + "!\n");
			FreeSensor();
			return;
		}

		// For ISO/Ansi
		int nFmt = 0; // Ansi
		// TODO: Averiguar el funcionamiento del switch y como modificarlo para el front
		/*
		 * if (radioISO.isSelected()) { nFmt = 1; // ISO }
		 */
		FingerprintSensorEx.DBSetParameter(mhDB, 5010, nFmt);
		// For ISO/Ansi End
		// set fakefun off
		// FingerprintSensorEx.SetParameter(mhDevice, 2002, changeByte(nFakeFunOn), 4);

		byte[] paramValue = new byte[4];
		int[] size = new int[1];
		// GetFakeOn
		// size[0] = 4;
		// FingerprintSensorEx.GetParameters(mhDevice, 2002, paramValue, size);
		// nFakeFunOn = byteArrayToInt(paramValue);

		size[0] = 4;
		FingerprintSensorEx.GetParameters(mhDevice, 1, paramValue, size);
		fpWidth = byteArrayToInt(paramValue);
		size[0] = 4;
		FingerprintSensorEx.GetParameters(mhDevice, 2, paramValue, size);
		fpHeight = byteArrayToInt(paramValue);

		imgbuf = new byte[fpWidth * fpHeight];
		// btnImg.resize(fpWidth, fpHeight);
		mbStop = false;
		workThread = new WorkThread();
		workThread.start();
		System.out.println("Open succ! Finger Image Width:" + fpWidth + ",Height:" + fpHeight + "\n");
	}

	private void btnEnrollActionPerformed() {// GEN-FIRST:event_btnEnrollActionPerformed
		if (0 == mhDevice) {
			System.out.println("Please Open device first!\n");
			return;
		}
		if (!bRegister) {
			enroll_idx = 0;
			bRegister = true;
			System.out.println("Please your finger 3 times!\n");
		}
	}

	private void btnIdentifyActionPerformed() {// GEN-FIRST:event_btnIdentifyActionPerformed
		if (0 == mhDevice) {
			System.out.println("Please Open device first!\n");
			return;
		}
		if (bRegister) {
			enroll_idx = 0;
			bRegister = false;
		}
		if (!bIdentify) {
			bIdentify = true;
		}
	}

	private void btnRegImgActionPerformed() {// GEN-FIRST:event_btnRegImgActionPerformed
		if (0 == mhDB) {
			System.out.println("Please open device first!\n");
		}
		String path = "d:\\test\\fingerprint.bmp";
		byte[] fpTemplate = new byte[2048];
		int[] sizeFPTemp = new int[1];
		sizeFPTemp[0] = 2048;
		int ret = FingerprintSensorEx.ExtractFromImage(mhDB, path, 500, fpTemplate, sizeFPTemp);
		if (0 == ret) {
			ret = FingerprintSensorEx.DBAdd(mhDB, iFid, fpTemplate);
			if (0 == ret) {
				// String base64 = fingerprintSensor.BlobToBase64(fpTemplate, sizeFPTemp[0]);
				iFid++;
				cbRegTemp = sizeFPTemp[0];
				System.arraycopy(fpTemplate, 0, lastRegTemp, 0, cbRegTemp);
				// Base64 Template
				// String strBase64 = Base64.encodeToString(regTemp, 0, ret, Base64.NO_WRAP);
				System.out.println("enroll succ\n");
			} else {
				System.out.println("DBAdd fail, ret=" + ret + "\n");
			}
		} else {
			System.out.println("ExtractFromImage fail, ret=" + ret + "\n");
		}
	}

	private void btnVerImgActionPerformed() {// GEN-FIRST:event_btnVerImgActionPerformed
		if (0 == mhDB) {
			System.out.println("Please open device first!\n");
		}
		String path = "d:\\test\\fingerprint.bmp";
		byte[] fpTemplate = new byte[2048];
		int[] sizeFPTemp = new int[1];
		sizeFPTemp[0] = 2048;
		int ret = FingerprintSensorEx.ExtractFromImage(mhDB, path, 500, fpTemplate, sizeFPTemp);
		if (0 == ret) {
			if (bIdentify) {
				int[] fid = new int[1];
				int[] score = new int[1];
				ret = FingerprintSensorEx.DBIdentify(mhDB, fpTemplate, fid, score);
				if (ret == 0) {
					System.out.println("Identify succ, fid=" + fid[0] + ",score=" + score[0] + "\n");
				} else {
					System.out.println("Identify fail, errcode=" + ret + "\n");
				}
			} else {
				if (cbRegTemp <= 0) {
					System.out.println("Please register first!\n");
				} else {
					ret = FingerprintSensorEx.DBMatch(mhDB, lastRegTemp, fpTemplate);
					if (ret > 0) {
						System.out.println("Verify succ, score=" + ret + "\n");
					} else {
						System.out.println("Verify fail, ret=" + ret + "\n");
					}
				}
			}
		} else {
			System.out.println("ExtractFromImage fail, ret=" + ret + "\n");
		}
	}
	
	private void btnCloseActionPerformed() {// GEN-FIRST:event_btnCloseActionPerformed
		FreeSensor();
		System.out.println("Close succ!\n");
	}

	private void FreeSensor() {
		mbStop = true;
		try { // wait for thread stopping
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (0 != mhDB) {
			FingerprintSensorEx.DBFree(mhDB);
			mhDB = 0;
		}
		if (0 != mhDevice) {
			FingerprintSensorEx.CloseDevice(mhDevice);
			mhDevice = 0;
		}
		FingerprintSensorEx.Terminate();
	}

	public static int byteArrayToInt(byte[] bytes) {
		int number = bytes[0] & 0xFF;
		number |= ((bytes[1] << 8) & 0xFF00);
		number |= ((bytes[2] << 16) & 0xFF0000);
		number |= ((bytes[3] << 24) & 0xFF000000);
		return number;
	}

	private void OnCatpureOK(byte[] imgBuf) {
		try {
			writeBitmap(imgBuf, fpWidth, fpHeight, "fingerprint.bmp");
			// btnImg.setIcon(new ImageIcon(ImageIO.read(new File("fingerprint.bmp"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeBitmap(byte[] imageBuf, int nWidth, int nHeight, String path) throws IOException {
		java.io.FileOutputStream fos = new java.io.FileOutputStream(path);
		java.io.DataOutputStream dos = new java.io.DataOutputStream(fos);

		int w = (((nWidth + 3) / 4) * 4);
		int bfType = 0x424d;
		int bfSize = 54 + 1024 + w * nHeight;
		int bfReserved1 = 0;
		int bfReserved2 = 0;
		int bfOffBits = 54 + 1024;

		dos.writeShort(bfType);
		dos.write(changeByte(bfSize), 0, 4);
		dos.write(changeByte(bfReserved1), 0, 2);
		dos.write(changeByte(bfReserved2), 0, 2);
		dos.write(changeByte(bfOffBits), 0, 4);

		int biSize = 40;
		int biWidth = nWidth;
		int biHeight = nHeight;
		int biPlanes = 1;
		int biBitcount = 8;
		int biCompression = 0;
		int biSizeImage = w * nHeight;
		int biXPelsPerMeter = 0;
		int biYPelsPerMeter = 0;
		int biClrUsed = 0;
		int biClrImportant = 0;

		dos.write(changeByte(biSize), 0, 4);
		dos.write(changeByte(biWidth), 0, 4);
		dos.write(changeByte(biHeight), 0, 4);
		dos.write(changeByte(biPlanes), 0, 2);
		dos.write(changeByte(biBitcount), 0, 2);
		dos.write(changeByte(biCompression), 0, 4);
		dos.write(changeByte(biSizeImage), 0, 4);
		dos.write(changeByte(biXPelsPerMeter), 0, 4);
		dos.write(changeByte(biYPelsPerMeter), 0, 4);
		dos.write(changeByte(biClrUsed), 0, 4);
		dos.write(changeByte(biClrImportant), 0, 4);

		for (int i = 0; i < 256; i++) {
			dos.writeByte(i);
			dos.writeByte(i);
			dos.writeByte(i);
			dos.writeByte(0);
		}

		byte[] filter = null;
		if (w > nWidth) {
			filter = new byte[w - nWidth];
		}

		for (int i = 0; i < nHeight; i++) {
			dos.write(imageBuf, (nHeight - 1 - i) * nWidth, nWidth);
			if (w > nWidth)
				dos.write(filter, 0, w - nWidth);
		}
		dos.flush();
		dos.close();
		fos.close();
	}

	public static byte[] changeByte(int data) {
		return intToByteArray(data);
	}

	public static byte[] intToByteArray(final int number) {
		byte[] abyte = new byte[4];
		abyte[0] = (byte) (0xff & number);
		abyte[1] = (byte) ((0xff00 & number) >> 8);
		abyte[2] = (byte) ((0xff0000 & number) >> 16);
		abyte[3] = (byte) ((0xff000000 & number) >> 24);
		return abyte;
	}

	private void OnExtractOK(byte[] template, int len) {
		if (bRegister) {
			int[] fid = new int[1];
			int[] score = new int[1];
			int ret = FingerprintSensorEx.DBIdentify(mhDB, template, fid, score);
			if (ret == 0) {
				System.out.println("the finger already enroll by " + fid[0] + ",cancel enroll\n");
				bRegister = false;
				enroll_idx = 0;
				return;
			}
			if (enroll_idx > 0 && FingerprintSensorEx.DBMatch(mhDB, regtemparray[enroll_idx - 1], template) <= 0) {
				System.out.println("please press the same finger 3 times for the enrollment\n");
				return;
			}
			System.arraycopy(template, 0, regtemparray[enroll_idx], 0, 2048);
			enroll_idx++;
			if (enroll_idx == 3) {
				int[] _retLen = new int[1];
				_retLen[0] = 2048;
				byte[] regTemp = new byte[_retLen[0]];

				if (0 == (ret = FingerprintSensorEx.DBMerge(mhDB, regtemparray[0], regtemparray[1], regtemparray[2],
						regTemp, _retLen)) && 0 == (ret = FingerprintSensorEx.DBAdd(mhDB, iFid, regTemp))) {
					iFid++;
					cbRegTemp = _retLen[0];
					System.arraycopy(regTemp, 0, lastRegTemp, 0, cbRegTemp);
					// Base64 Template
					System.out.println("enroll succ:\n");
				} else {
					System.out.println("enroll fail, error code=" + ret + "\n");
				}
				bRegister = false;
			} else {
				System.out.println("You need to press the " + (3 - enroll_idx) + " times fingerprint\n");
			}
		} else {
			if (bIdentify) {
				int[] fid = new int[1];
				int[] score = new int[1];
				int ret = FingerprintSensorEx.DBIdentify(mhDB, template, fid, score);
				if (ret == 0) {
					System.out.println("Identify succ, fid=" + fid[0] + ",score=" + score[0] + "\n");
				} else {
					System.out.println("Identify fail, errcode=" + ret + "\n");
				}

			} else {
				if (cbRegTemp <= 0) {
					System.out.println("Please register first!\n");
				} else {
					int ret = FingerprintSensorEx.DBMatch(mhDB, lastRegTemp, template);
					if (ret > 0) {
						System.out.println("Verify succ, score=" + ret + "\n");
					} else {
						System.out.println("Verify fail, ret=" + ret + "\n");
					}
				}
			}
		}
	}

	private class WorkThread extends Thread {
		@Override
		public void run() {
			super.run();
			int ret = 0;
			while (!mbStop) {
				templateLen[0] = 2048;
				if (0 == (ret = FingerprintSensorEx.AcquireFingerprint(mhDevice, imgbuf, template, templateLen))) {
					if (nFakeFunOn == 1) {
						byte[] paramValue = new byte[4];
						int[] size = new int[1];
						size[0] = 4;
						int nFakeStatus = 0;
						// GetFakeStatus
						ret = FingerprintSensorEx.GetParameters(mhDevice, 2004, paramValue, size);
						nFakeStatus = byteArrayToInt(paramValue);
						System.out.println("ret = " + ret + ",nFakeStatus=" + nFakeStatus);
						if (0 == ret && (byte) (nFakeStatus & 31) != 31) {
							System.out.println("Is a fake finger?\n");
							return;
						}
					}
					OnCatpureOK(imgbuf);
					OnExtractOK(template, templateLen[0]);
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}
}
