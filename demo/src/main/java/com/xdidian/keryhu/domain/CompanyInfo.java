package com.xdidian.keryhu.domain;

import java.io.Serializable;

import com.xdidian.keryhu.tree.DepartmentTreeNode;

import lombok.Data;

/**
 * 
* @ClassName: CompanyInfo
* @Description: TODO(员工所在公司的基本信息，包含公司名字，地址，管理员，员工Id，员工职位，营业执照图片)
* @author keryhu  keryhu@hotmail.com
* @date 2016年7月28日 下午1:02:50
 */
@Data
public class CompanyInfo implements Serializable{

	private static final long serialVersionUID = -1554578596908142213L;
	
	private String id="123";
	private String companyName="上海利好公司";
	private String address="an ting ";
	private String adminId="11";
	private String userId="22";
	private DepartmentTreeNode<Department> department;
	
	public CompanyInfo(){
		DepartmentTreeNode<Department> n1 = new DepartmentTreeNode<>(new Department("111"));
		DepartmentTreeNode<Department> n2 = new DepartmentTreeNode<>(new Department("222"));
		DepartmentTreeNode<Department> n3 = new DepartmentTreeNode<>(new Department("333"));
		DepartmentTreeNode<Department> n4 = new DepartmentTreeNode<>(new Department("444"));
		DepartmentTreeNode<Department> n5 = new DepartmentTreeNode<>(new Department("555"));
		DepartmentTreeNode<Department> n6 = new DepartmentTreeNode<>(new Department("666"));
		DepartmentTreeNode<Department> n7 = new DepartmentTreeNode<>(new Department("777"));

		n1.add(n2);
		n1.add(n3);
		n1.add(n4);
		n2.add(n5);
		n2.add(n6);
		n4.add(n7);
		this.department=n1;
	}
	

}
