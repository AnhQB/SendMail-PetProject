package fsoft.jits.mybatis.dto;

public class ProductDTO {
	private int id;
	private int categoryId;
	private int subcategoryId;
	private String name;
	private String price;
	private byte[] image;
	private String imageBase64;
	public ProductDTO() {
		super();
	}
	
	
	public ProductDTO(int id, int categoryId, int subcategoryId, String name, String price, byte[] image,
			String imageBase64) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.name = name;
		this.price = price;
		this.image = image;
		this.imageBase64 = imageBase64;
	}

	

	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getSubcategoryId() {
		return subcategoryId;
	}
	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImageBase64() {
		return imageBase64;
	}
	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
	
	
}
