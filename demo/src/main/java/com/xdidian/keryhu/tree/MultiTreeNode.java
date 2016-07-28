package com.xdidian.keryhu.tree;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;


/**
 * 
* @ClassName: MultiTreeNode
* @Description: TODO(代表多维节点的node，表示一个节点下面有多于两个节点的tree)
* @author keryhu  keryhu@hotmail.com
* @date 2016年7月28日 上午10:08:36
* @param <T>
 */

public abstract class MultiTreeNode<T> extends TreeNode<T> {


	private static final long serialVersionUID = -9081136739831798056L;

	
	public MultiTreeNode(T data) {
		super(data);
	}

	/**
	 * 将参数中的节点和他的后台，加入到当前节点的下面作为后代。
	 */
	
	public boolean addSubtrees(Collection<? extends MultiTreeNode<T>> subtrees) {
		if (areAllNulls(subtrees)) {
			return false;
		}
		for (MultiTreeNode<T> subtree : subtrees) {
			linkParent(subtree, this);
			if (!add(subtree)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 返回当前节点的所有兄弟节点
	 */
	
	public Collection<? extends MultiTreeNode<T>> siblings() {
		if (isRoot()) {
			String message = String.format("Unable to find the siblings. The tree node %1$s is root", root());
			throw new TreeNodeException(message);
		}
		Collection<? extends TreeNode<T>> parentSubtrees = parent.subtrees();
		int parentSubtreesSize = parentSubtrees.size();
		if (parentSubtreesSize == 1) {
			return Collections.emptySet();
		}
		Collection<MultiTreeNode<T>> siblings = new HashSet<>(parentSubtreesSize - 1);
		for (TreeNode<T> parentSubtree : parentSubtrees) {
			if (!parentSubtree.equals(this)) {
				siblings.add((MultiTreeNode<T>) parentSubtree);
			}
		}
		return siblings;
	}

	/**
	 * 检测参数中的节点和他的后代，是否存在于当前节点的后代中
	 * 
	 */
	
	public boolean hasSubtrees(Collection<? extends MultiTreeNode<T>> subtrees) {
		if (isLeaf()
				|| areAllNulls(subtrees)) {
			return false;
		}
		for (MultiTreeNode<T> subtree : subtrees) {
			if (!this.hasSubtree(subtree)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 从当前节点中删除指定的节点和所有的后代。
	 *
	 */
	public boolean dropSubtrees(Collection<? extends MultiTreeNode<T>> subtrees) {
		if (isLeaf()
				|| areAllNulls(subtrees)) {
			return false;
		}
		boolean result = false;
		for (MultiTreeNode<T> subtree : subtrees) {
			boolean currentResult = dropSubtree(subtree);
			if (!result && currentResult) {
				result = true;
			}
		}
		return result;
	}

}