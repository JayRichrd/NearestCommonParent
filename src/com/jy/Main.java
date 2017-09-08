package com.jy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// 构建树结构
		TreeNode treeNode1 = new TreeNode("J");
		TreeNode treeNode2 = new TreeNode("I");
		TreeNode treeNode3 = new TreeNode("H");
		TreeNode treeNode4 = new TreeNode("G");
		TreeNode treeNode5 = new TreeNode("F");
		TreeNode treeNode6 = new TreeNode("E");
		treeNode6.children.add(treeNode3);
		treeNode6.children.add(treeNode2);
		treeNode6.children.add(treeNode1);
		TreeNode treeNode7 = new TreeNode("D");
		treeNode7.children.add(treeNode5);
		treeNode7.children.add(treeNode4);
		TreeNode treeNode8 = new TreeNode("C");
		TreeNode treeNode9 = new TreeNode("B");
		treeNode9.children.add(treeNode7);
		treeNode9.children.add(treeNode6);
		TreeNode treeNode10 = new TreeNode("A");
		treeNode10.children.add(treeNode9);
		treeNode10.children.add(treeNode8);

		TreeNode root = treeNode10;

		// 获取从根节点到两个节点的路径
		List<TreeNode> path1 = new LinkedList<TreeNode>();
		getNodePath(root, treeNode3, path1);
		List<TreeNode> path2 = new LinkedList<TreeNode>();
		getNodePath(root, treeNode4, path2);

		if (!path1.isEmpty() && !path2.isEmpty()) {
			System.out.print("从根节点到节点" + treeNode3.value + "的路径:");
			for (int i = 0; i < path1.size() - 1; i++)
				System.out.print(path1.get(i).value + "->");
			System.out.println(path1.get(path1.size() - 1).value);

			System.out.print("从根节点到节点" + treeNode4.value + "的路径:");
			for (int i = 0; i < path2.size() - 1; i++)
				System.out.print(path2.get(i).value + "->");
			System.out.println(path2.get(path2.size() - 1).value);

			TreeNode nearestCommonParent = getNearestCommonParent(path1, path2);
			if (nearestCommonParent != null)
				System.out.println(
						"节点" + treeNode3.value + "和节点" + treeNode4.value + "的最低公共祖先:" + nearestCommonParent.value);
			else
				System.out.println("节点" + treeNode3.value + "和节点" + treeNode4.value + "不存在公共祖先!!!");

		} else {
			System.out.println("树中不存在需要查找的节点!!!");
		}

	}

	/**
	 * 寻找从根节点到目标节点的路径
	 * 
	 * @param root
	 *            根节点
	 * @param target
	 *            目标节点
	 * @param path
	 *            路径
	 * @return 返回是否已经在子节点中找到了目标节点，如果找到就返回true,否则返回false
	 */
	public static boolean getNodePath(TreeNode root, TreeNode target, List<TreeNode> path) {
		// 鲁棒性检查
		if (root == null || target == null)
			return false;
		// 现将节点加入到路径中
		path.add(root);
		// 比较当前节点与目标 节点
		if (root == target)
			return true;
		// 寻找的结果
		boolean found = false;
		// 取出子节点挨个继续往下递归寻找目标节点
		List<TreeNode> children = root.children;
		for (TreeNode treeNode : children) {
			found = getNodePath(treeNode, target, path);
			if (found)
				break;
		}
		// 当前节点往下都没有找到目标节点
		// 将当前节点移除路径，并返回继续寻找
		if (!found)
			path.remove(path.size() - 1);
		return found;
	}

	/**
	 * 获取两条路径上最近的公共祖先
	 * 
	 * @param path1
	 *            路径1
	 * @param path2
	 *            路径2
	 * @return 返回取到的最近公共祖先
	 */
	public static TreeNode getNearestCommonParent(List<TreeNode> path1, List<TreeNode> path2) {
		Iterator<TreeNode> iterator1 = path1.iterator();
		Iterator<TreeNode> iterator2 = path2.iterator();
		// 最近公共祖先
		TreeNode nearestCommonParent = null;
		// 从两个路径的头开始往下比较，直到从两个路径中的节点不相等
		while (iterator1.hasNext() && iterator2.hasNext()) {
			TreeNode temp = iterator1.next();
			if (temp == iterator2.next())
				nearestCommonParent = temp;
			else
				break;
		}
		return nearestCommonParent;
	}

}
