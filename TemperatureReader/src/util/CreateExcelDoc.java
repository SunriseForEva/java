package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class CreateExcelDoc {

	private Date currentDate;
	private String currentFile = new String(/*"D://Database//DB.xls"*/"C://DB.xls");
	private String tempFile = new String(/*"D://Database//tempDB.xls"*/"C://tempDB.xls");
	private File file;
	private File temp;
	private Workbook book;
	private WritableWorkbook copy;
	
	@SuppressWarnings("unused")
	private int countOfLines ;
	
	public static final int READ = 0;
	public static final int WRITE = 1;
	
	public CreateExcelDoc(int choice) throws BiffException, IOException {
		if(choice == 1){
			file = new File(currentFile);
			temp = new File(tempFile);
			book = Workbook.getWorkbook(file);
			copy = Workbook.createWorkbook(temp, book);
		}
		if(choice == 0){
			file = new File(currentFile);
			book = Workbook.getWorkbook(file);
		}
	}
	
	public void copyFiles(File target, File temp) throws IOException{
		FileInputStream fis = new FileInputStream(temp);
		byte[] arr = new byte[fis.available()];
		fis.read(arr);
		fis.close();
		
		FileOutputStream fos = new FileOutputStream(target);
		fos.write(arr);
		fos.flush();
		fos.close();
		
	}
	
	public void writeToExcel(ArrayList<Double> temperature) throws IOException, WriteException, BiffException{
		int count  = Integer.parseInt(copy.getSheet(0).getCell(0, 0).getContents());
		int countTemp = count;
		
		for (int i = 1 ; i < temperature.size(); i++) {
            copy.getSheet(0).addCell(new Label(i, countTemp + 1, String.valueOf(temperature.get(i))));
        }
        currentDate = new Date();
        copy.getSheet(0).addCell(new Label(temperature.size(), countTemp + 1, currentDate.toString()));

        count++;
        copy.getSheet(0).addCell(new Label(0, 0, String.valueOf(count)));

		copy.write();
		copy.close();
		
		copyFiles(file, temp);
		temp.delete();
	}
	
	public int getCountOfLines(){
		int count  = Integer.parseInt(book.getSheet(0).getCell(0, 0).getContents()) - 1;
		return count;
	}
	
	public ArrayList<Object> readLineFromExcel(int numberOfLine) throws IOException{ //The method is reading from excel file assigned line
		ArrayList<Object> line = new ArrayList<Object>();
		for (int i = 1; i < 12; i++) {
			line.add(Double.valueOf(book.getSheet(0).getCell(i, numberOfLine+1).getContents()));
		}
		line.add(Long.valueOf(book.getSheet(0).getCell(12, numberOfLine+1).getContents()));
		return line;
	}
	
}
