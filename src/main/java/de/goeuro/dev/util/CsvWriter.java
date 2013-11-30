package de.goeuro.dev.util;

import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import de.goeuro.dev.dsl.geo.Pots;
import de.goeuro.dev.dsl.geo.Results;

public class CsvWriter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CsvWriter.class);

	public static void writeCSV(Results results) {

		String[] header = { "_type", "_id", "name", "type", "latitude",
				"longitude" };

		Pots[] pots = null;
		String[][] csvMatrix = null;

		if (results != null) {
			pots = results.getPots();

			// you can assemble this 2D array however you want

			int row = results.getPots().length;
			int column = header.length;

			csvMatrix = new String[row][column];

			for (int i = 0; i < row; i++) {

				csvMatrix[i][0] = pots[i].get_type();
				csvMatrix[i][1] = pots[i].get_id() + "";
				csvMatrix[i][2] = pots[i].getName();
				csvMatrix[i][3] = pots[i].getType();
				csvMatrix[i][4] = pots[i].getGeoPosition().getLatitude() + "";
				csvMatrix[i][5] = pots[i].getGeoPosition().getLongitude() + "";

			}
		}
		writeCsv(header, csvMatrix);

	}

	private static void writeCsv(String[] header, String[][] csvMatrix) {
		if (logger.isDebugEnabled()) {
			logger.debug("writeCsv(String[], String[][]) - start"); //$NON-NLS-1$
		}
		String fileName = "out.csv";
		
		logger.info("writeCsv(String[] : writing file csv :"+fileName);

		ICsvListWriter csvWriter = null;
		try {
			csvWriter = new CsvListWriter(new FileWriter(fileName),
					CsvPreference.STANDARD_PREFERENCE);
			csvWriter.writeHeader(header);

			if (csvMatrix != null) {
				for (int i = 0; i < csvMatrix.length; i++) {
					csvWriter.write(csvMatrix[i]);
				}
			} else {
				logger.warn("results set empty");
			}

		} catch (IOException e) {
			logger.error("writeCsv(String[], String[][])" + e.getMessage()); //$NON-NLS-1$

		} finally {
			try {
				csvWriter.close();
			} catch (IOException e) {
				logger.warn("writeCsv(String[], String[][]) - exception ignored" + e.getMessage()); //$NON-NLS-1$
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("writeCsv(String[], String[][]) - end"); //$NON-NLS-1$
		}
	}

}
