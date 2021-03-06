/*
 * Copyright (C) 2004-2007
 * Takashi Okamoto <tora@debian.org>
 * Tsuyoshi Fukui <fukui556@oki.com>
 * Matt Francis <asbel@neosheffield.co.uk>
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 */


package net.java.sen.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * parse CSV file and extract token.
 */
public class CSVParser {

	/**
	 * The Reader from which lines of CSV data are read
	 */
	private BufferedReader reader = null;

	/**
	 * The current line of CSV data
	 */
	private String line = null;

	/**
	 * The current position within the current line
	 */
	private int position = 0;


	/**
	 * Advances to the next line of CSV data, if any, skipping any remaining
	 * values on the current row
	 *
	 * @return <code>true</code> if a line was read; false othewise
	 * @throws IOException
	 */
	public boolean nextRow() throws IOException {

		this.line = this.reader.readLine();

		if ((this.line == null) || (this.line.length() == 0)) {
			return false;
		}

		this.position = 0;
		return true;

	}


	/**
	 * Reads the next value from the current line
	 * 
	 * @return token The value
	 */
	public String nextToken() {

		int start;
		boolean quote = false;

		if ((this.line == null) || (this.position >= this.line.length())) {
			return null;
		}

		if (this.line.charAt(this.position) == '\"') {
			quote = true;
			this.position++;
		}
		start = this.position;

		while (this.position < this.line.length()) {

			if (this.line.charAt(this.position) == ',' && !quote) {
				return this.line.substring(start, this.position++);
			} else if (this.line.charAt(this.position) == '\"' && quote) {
				if ((this.position + 1 < this.line.length()) && (this.line.charAt(this.position + 1) == '\"')) {
					this.position += 2;
					continue;
				}
				String ret = this.line.substring(start, this.position).replaceAll("\"\"", "\"");
				this.position += 2;
				return ret;
			}
			this.position++;

		}

		return this.line.substring(start, this.position);

	}


	/**
	 * Returns an array of all values from the next line of the input
	 * 
	 * @return tokens The values
	 * @throws IOException 
	 */
	public String[] nextTokens() throws IOException {

		ArrayList<String> list = new ArrayList<String>();

		if (nextRow() == false) {
			return null;
		}

		String input;
		while ((input = nextToken()) != null) {
			list.add(input);
		}

		String tokens[] = list.toArray(new String[0]);

		return tokens;

	}


	/**
	 * Returns the unparsed current line of text
	 *
	 * @return The line of text
	 */
	public String currentLine() {
		
		return this.line;

	}


	/**
	 * Constructor for a parser that reads lines from an InputStream
	 * 
	 * @param inputStream The InputStream to read from
	 * @param charset The charset of the InputStream
	 * @throws IOException 
	 */
	public CSVParser(InputStream inputStream, String charset) throws IOException {
	
		this.reader = new BufferedReader(new InputStreamReader(inputStream, charset));

	}


	/**
	 * Constructor for a parser that reads lines from a String
	 * 
	 * @param string The string to read from
	 */
	public CSVParser(String string) {

		this.reader = new BufferedReader(new StringReader(string));

	}


}
