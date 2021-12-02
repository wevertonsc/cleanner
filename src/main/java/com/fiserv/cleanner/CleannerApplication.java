package com.fiserv.cleanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class CleannerApplication {

	public static void main(String[] args) {
		//SpringApplication.run(CleannerApplication.class, args);

		if(args.length<3) {
			// - - Usage - -
			System.out.println("  -  - Usage -  - " + "\n");
			System.out.println("java -jar cleanner <file name> <initial position to cut> <line length> ");
			System.out.println("\n" +"  -  - - - - -  - ");
		}

		String inputFile = args[0];
		String outputFile = args[0] + ".out";
		int initialPos = Integer.parseInt(args[1]);
		int length = Integer.parseInt(args[2]) - 1;
		String s;

		try {
			FileWriter fw = new FileWriter(outputFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			String initialString = "";
			String finalString = "";

			try {
				FileReader fr = new FileReader(inputFile);
				BufferedReader br = new BufferedReader(fr);

				while ((s = br.readLine()) != null) {
					System.out.println(s.length());
					if (s.length() > length) {
						initialString = s.substring(0, initialPos);
						finalString = s.substring(initialPos + (s.length() - length));
						bw.write(initialString + finalString);
					}else {
						bw.write(s.substring(0,length));
					}
					bw.newLine();
				}
				br.close();

			} catch (FileNotFoundException e) {
				System.out.println("File was not found!");
			} catch (IOException e) {
				System.out.println("No file found!");
			}
			bw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error1!");
		} catch (IOException e) {
			System.out.println("Error2!");
		}
	}
}
