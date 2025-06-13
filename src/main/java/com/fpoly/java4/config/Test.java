package com.fpoly.java4.config;

public class Test extends Student implements Config, ConfigContext {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
//	ghi đè 
	public void setName() {

	}

//	Nạp chồng 
	public void setName(String name) {

	}

	@Override
	public void getVersion() {
		// TODO Auto-generated method stub

	}

}

interface Config extends ConfigContext {
// Tất cả các biến và phương thức đều là public 
// Tất cả các biến khi khai báo bên trong inteface
// phải là constants và được truy cập không cần khai báo lớp
	static final int VERSION = 1;

// Tất cả các phương thức bên trong interface đều không được định nghĩa 
	void getVersion();
}

interface ConfigContext {

}

abstract class Student {

	public abstract String getName();

	public void setName() {

	}
}