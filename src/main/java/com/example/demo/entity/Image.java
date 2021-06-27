package com.example.demo.entity;

public class Image {
	private String name;
	private String data;
	
	public Image() {
	}

	public Image(String name, String data) {
		super();
		this.name = name;
		this.data = data;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
//		String info = String.format("Image name = %s, data = %s", name, data);
//		System.out.println("Image.toString(): " + info);
		return "toString";
//		return "Image [name=" + name + ", data=" + data + "]";
	}
	
	
}
