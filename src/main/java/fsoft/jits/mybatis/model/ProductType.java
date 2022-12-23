package fsoft.jits.mybatis.model;


public class ProductType {
	private int id;
	private int productId;
	private String videoCard;
	private boolean procesorType;
	private float memory;
	private float putere;
	private float maximumPreasure;
	private float waterFilter;
	public ProductType() {
		super();
	}
	
	public ProductType(int id, int productId, String videoCard, boolean procesorType, float memory, float putere,
			float maximumPreasure, float waterFilter) {
		super();
		this.id = id;
		this.productId = productId;
		this.videoCard = videoCard;
		this.procesorType = procesorType;
		this.memory = memory;
		this.putere = putere;
		this.maximumPreasure = maximumPreasure;
		this.waterFilter = waterFilter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getVideoCard() {
		return videoCard;
	}

	public void setVideoCard(String videoCard) {
		this.videoCard = videoCard;
	}

	public boolean isProcesorType() {
		return procesorType;
	}

	public void setProcesorType(boolean procesorType) {
		this.procesorType = procesorType;
	}

	public float getMemory() {
		return memory;
	}

	public void setMemory(float memory) {
		this.memory = memory;
	}

	public float getPutere() {
		return putere;
	}

	public void setPutere(float putere) {
		this.putere = putere;
	}

	public float getMaximumPreasure() {
		return maximumPreasure;
	}

	public void setMaximumPreasure(float maximumPreasure) {
		this.maximumPreasure = maximumPreasure;
	}

	public float getWaterFilter() {
		return waterFilter;
	}

	public void setWaterFilter(float waterFilter) {
		this.waterFilter = waterFilter;
	}
	
	
	
}
