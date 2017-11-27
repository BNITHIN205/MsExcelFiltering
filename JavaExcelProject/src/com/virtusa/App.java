package com.virtusa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

//author nithin
public class App {

	
	//main method
	public static void main(String[] args) {
		File f = null;
		InputStream is = null;
		try {
			f = new File("D://java/Auto.xls");
			is = new FileInputStream(f);
			HSSFWorkbook workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			ArrayList<Auto> autoList = new ArrayList<Auto>();
			for (int currentRow = 1; currentRow < lastRowNum; currentRow++) {
				HSSFRow row = sheet.getRow(currentRow);
				int lastCellNum = row.getLastCellNum();

				Auto a = new Auto();
				for(int i=0; i<lastCellNum; i++ ) {
					HSSFCell cell = row.getCell(i);
					String value = cell.toString();
					switch(i) {
						case 0: a.setMpg(value);break;
						case 1: a.setCylinders(value);break;
						case 2: a.setDisplacement(value);break;
						case 3: a.setHorsepower(value);break;
						case 4: a.setWeight(value);break;
						case 5: a.setAcceleration(value);break;
						case 6: a.setYear(value);break;
						case 7: a.setOrigin(value);break;
						case 8: a.setName(value);break;
					}
				}

				autoList.add(a);
			}

			System.out.println("Total : "+autoList.size());
			
			Iterator<Auto> iterator = autoList.iterator();
			ArrayList<Auto> filteredList = new ArrayList<Auto>();
			while (iterator.hasNext()) {
				Auto auto = (Auto) iterator.next();
				boolean isMatchFound = false;
				if("24.0".equalsIgnoreCase(auto.getMpg())) {
					if("4.0".equalsIgnoreCase(auto.getCylinders())) {
						if("113.0".equalsIgnoreCase(auto.getDisplacement())) {
							//filteredList.add(auto);
							isMatchFound = true;
							System.out.println("Hi, match found");
						}
					}
				}
				if(!isMatchFound) {
					iterator.remove();
				}
			}

			System.out.println("Filtered : "+autoList.size());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

