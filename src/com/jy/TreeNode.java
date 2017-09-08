package com.jy;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {
	// 节点值
	public Object value;
	// 节点的子节点列表
	public List<TreeNode> children = new LinkedList<TreeNode>();
	
	public TreeNode(Object value) {
		super();
		this.value = value;
	}
	
}
