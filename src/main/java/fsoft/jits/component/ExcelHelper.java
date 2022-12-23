package fsoft.jits.component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import fsoft.jits.mybatis.dto.ProductDTO;
import fsoft.jits.mybatis.model.Product;

@Component
public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static String END = ".xlsx";

	public static ByteArrayInputStream exportToExcel(List<Product> listObject, String name) {	
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(name);
			// Header
			Row headerRow = sheet.createRow(0);
			Field[] headers = listObject.get(0).getClass().getDeclaredFields();
			for (int col = 0; col < headers.length; col++) {
				Cell cell = headerRow.createCell(col);
				String header = headers[col].getName();
				cell.setCellValue(header);
			}

			int rowIdx = 1;
			int colIdx = 0;
			for (Product product : listObject) {
				colIdx = 0;
				Row row = sheet.createRow(rowIdx++);
				
				row.createCell(colIdx++).setCellValue(product.getId());
				row.createCell(colIdx++).setCellValue(product.getCategoryId());
				row.createCell(colIdx++).setCellValue(product.getSubcategoryId());
				row.createCell(colIdx++).setCellValue(product.getName());
				row.createCell(colIdx++).setCellValue(product.getPrice());
				
				if(product.getImage() != null && product.getImage().length > 0) {
					int inputImagePictureID1 = workbook.addPicture(product.getImage(), Workbook.PICTURE_TYPE_PNG);
					XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
					XSSFClientAnchor imageAnchor = new XSSFClientAnchor();
					imageAnchor.setCol1(headers.length - 1);
					imageAnchor.setCol2(headers.length);
					imageAnchor.setRow1(rowIdx - 1);
					imageAnchor.setRow2(rowIdx);
					
					drawing.createPicture(imageAnchor, inputImagePictureID1);
					
				}
				
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}
	
	public static boolean hasExcelFormat(MultipartFile file) {
		if(!TYPE.equalsIgnoreCase(file.getContentType())) {
			return false;
		}
		return true;
	}
	
	public static List<Product> importFromExcel(InputStream input){
		List<Product> lstProduct = new ArrayList<>();
		Map<String, Integer> mapHeader = new HashMap<>();
		try {
			Workbook workbook = new XSSFWorkbook(input);
			
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			
			
			int rowNumber = 0;
			while(rows.hasNext()) {
				Row currentRow = rows.next();
				
				//check first row in excel (is header)
				if(rowNumber == 0) {
					rowNumber++;
					
					//get number column in excel
					short minColIx = currentRow.getFirstCellNum();
					short maxColIx = currentRow.getLastCellNum();
					
					//get each column in row header
					for(short colIx = minColIx; colIx < maxColIx; ++colIx) {
						Cell currentCell = currentRow.getCell(colIx);
						//put to map with key: name header | value: index
						mapHeader.put(currentCell.getStringCellValue().toLowerCase(), currentCell.getColumnIndex());
					}
					continue;
				}
								
				Product product;
				//get value each column
				int categoryId = (int) currentRow.getCell(mapHeader.get("categoryid")).getNumericCellValue();
				int subcategoryId = (int) currentRow.getCell(mapHeader.get("subcategoryid")).getNumericCellValue();
				String name = currentRow.getCell(mapHeader.get("name")).getStringCellValue();
				String price = String.valueOf(currentRow.getCell(mapHeader.get("price")).getNumericCellValue());
				byte[] image = getPictureByPosition(sheet, rowNumber, rowNumber + 1, mapHeader.get("image"), mapHeader.get("image") + 1);
				//get data for column image
//				PictureData pictureData = (PictureData) currentRow.getCell(mapHeader.get("image"));
//				if(pictureData == null) {
//					product = new Product(categoryId, subcategoryId, name, price, null);   
//				}else {
//					//String fileExtension = pictureData.suggestFileExtension();
//					byte[] imageData = pictureData.getData();
//					product = new Product(categoryId, subcategoryId, name, price, imageData);
//				}
				
				product = new Product(categoryId, subcategoryId, name, price, image);
				
				lstProduct.add(product);
				rowNumber++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lstProduct;
	}
	
	private static byte[] getPictureByPosition(Sheet sheet, int row1, int row2, int col1, int col2) {
		//get image
		XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
		for(XSSFShape shape : drawing.getShapes()) {
			if(shape instanceof XSSFPicture) {
				XSSFPicture picture = (XSSFPicture) shape;
				XSSFPictureData xssfPictureData = picture.getPictureData();
				ClientAnchor anchor = picture.getPreferredSize();
//				int row11 = anchor.getRow1();
//                int row21 = anchor.getRow2();
//                int col11 = anchor.getCol1();
//                int col21 = anchor.getCol2();
				if(anchor.getCol1() == col1 && anchor.getCol2() == col2 &&
						anchor.getRow1() == row1 && anchor.getRow2() == row2) {
					return xssfPictureData.getData();
				}
			}
		}
		
		return null;
	}
	
}
