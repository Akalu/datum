package org.datum.util;

import java.util.Arrays;
import org.datum.core.Pipeline;
import org.datum.datasource.DataProvider;
import org.datum.datasource.FieldSet;
import org.datum.datasource.model.DataSchema;
import org.datum.datasource.model.UnorderedStringFieldSet;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVLoader {

	public static final int batchSize = 2000;

	@Setter
	private char separator;

	/**
	 * Public constructor to build CSVLoader object with some details.
	 */
	public CSVLoader() {
		// Set default separator
		this.separator = ',';
	}

	/**
	 * Parse CSV file using OpenCSV library and feed data into consumer.
	 * 
	 * @param csvFile            Input CSV file
	 * @param truncateBeforeLoad Truncate the table before inserting new records.
	 * @throws Exception
	 */
	public void loadCSV(DataProvider dataProvider, DataSchema schema, Pipeline<FieldSet> pipe,
			boolean truncateBeforeLoad) throws Exception {

		try (CSVReader csvReader = new CSVReaderBuilder(dataProvider.getReader())
				.withCSVParser(new CSVParserBuilder().withSeparator(this.separator).build()).build()) {

			if (schema == null) {
				throw new IllegalArgumentException(
						"No columns defined in given CSV file. Please check the CSV file format.");
			}

			String[] headerLine = csvReader.readNext();
			if (headerLine == null) {
				log.warn("csv file is empty");
			}
			log.debug("read header: {}", Arrays.toString(headerLine));

			String[] nextLine;
			int count = 0;
			int totalCount = 0;
			int invalidCount = 0;
			
			Timer t = new Timer();
			
			while ((nextLine = csvReader.readNext()) != null) {
				totalCount++;
				if (null != nextLine) {
					try {
						log.debug("read : {}", Arrays.toString(nextLine));
						pipe.process(new UnorderedStringFieldSet(nextLine));
						count++;
					} catch (IllegalArgumentException e) {
						log.error(e.getMessage());
						invalidCount++;
					}
				}
				if (count % batchSize == 0) {

					log.debug("saving data, batch {}", count / batchSize);
				}
			}
			// insert remaining records

			log.debug("total records processed: {}", totalCount);
			log.debug("inserted: {}", count);
			log.debug("omitted: {}", invalidCount);
			t.end();

		} catch (Exception e) {
			throw new Exception("Error occured while executing file. " + e.getMessage());
		}

	}

}
