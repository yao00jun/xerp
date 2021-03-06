package com.nutzside.common.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.nutz.dao.entity.annotation.Column;

/**
 * 
 * @author Administrator
 *
 */
public class SimpleZTreeNode {
	@Column
	private Long id;
	@Column
	private String name;
	@Column
	private String title;
	@Column
	private boolean checked;
	@Column
	private boolean open;
	@Column
	private boolean isParent;

	public static SimpleZTreeNode getInstance(ResultSet rs) throws SQLException {
		SimpleZTreeNode simpleZTreeNode = new SimpleZTreeNode();
		simpleZTreeNode.id = rs.getLong("ID");
		simpleZTreeNode.name = rs.getString("NAME");
		simpleZTreeNode.title = rs.getString("TITLE");
		simpleZTreeNode.checked = rs.getBoolean("CHECKED");
		simpleZTreeNode.open = rs.getBoolean("OPEN");
		simpleZTreeNode.isParent = rs.getBoolean("ISPARENT");
		return simpleZTreeNode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
}