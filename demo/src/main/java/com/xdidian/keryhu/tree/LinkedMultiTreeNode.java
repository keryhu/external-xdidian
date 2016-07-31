package com.xdidian.keryhu.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * 
 * @author可以用来序列号使用，而且实现了多层的树结构
 */
public class LinkedMultiTreeNode<T> extends MultiTreeNode<T>{
	
	private static final long serialVersionUID = -2512183913417704255L;

	//当前节点，子节点中最左边的节点
	private LinkedMultiTreeNode<T> leftMostNode;
	
	//返回当前节点的右边的兄弟节点
	private LinkedMultiTreeNode<T> rightSiblingNode;
	
	//返回当前节点的最后一个子节点。
	private LinkedMultiTreeNode<T> lastSubtreeNode;
	
	public LinkedMultiTreeNode(T data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	//返回当前节点的后代节点，如果有的话，包含他们的后代
	@Override
	public Collection<? extends TreeNode<T>> subtrees() {
		// TODO Auto-generated method stub
		if(isLeaf()){
			return Collections.emptySet();				
		}
		Collection<TreeNode<T>> subtrees=new HashSet<>();
		subtrees.add(leftMostNode);
		LinkedMultiTreeNode<T> nextSubtree = leftMostNode.rightSiblingNode;
		//将右边的一个个兄弟节点加上。
		while(nextSubtree!=null){
			subtrees.add(nextSubtree);
			nextSubtree=nextSubtree.rightSiblingNode;
		}
		return subtrees;
	}

	/**
	 * 增加指定节点和他的后代到当前树,如果当前树发生改变，则返回true，否则false
	 */
	
	@Override
	public boolean add(TreeNode<T> subtree) {
		// TODO Auto-generated method stub
		if(subtree==null){
			return false;
		}
		//将指定节点挂到现在节点下
		linkParent(subtree,this);
		if(isLeaf()){
			//如果当前节点是一个枝叶，那么就将指定参数的节点，挂到下边的最左边，而去是 最后一个子节点
			leftMostNode=(LinkedMultiTreeNode<T>) subtree;
			lastSubtreeNode=leftMostNode;
		}else{
			//如果不是枝叶，那么指定参数的节点，挂在最后一个子节点的后边，从此这个指定参数的节点为最后一个子节点
			lastSubtreeNode.rightSiblingNode=(LinkedMultiTreeNode<T>) subtree;
			lastSubtreeNode=lastSubtreeNode.rightSiblingNode;
		}
		return true;
	}

	/**
	 * 从当前节点的子节点中，删除第一个满足指定参数节点的，
	 * 如果当前节点的数据，发生了改变，则返回true，否则返回false
	 * 
	 */
	
	@Override
	public boolean dropSubtree(TreeNode<T> subtree) {
		// TODO Auto-generated method stub
		//如果当前节点为null，或者是枝叶，或者是root，那么返回false
		if(subtree==null||isLeaf()||subtree.isRoot()){
			return false;
		}
		//如果想要删除的参数节点，是最左边的子节点，那么将最左边的子节点的右边一个节点移除
		//为最左边的子节点，并且将指定参数的节点从他的父节点中剥离出来。而且将他的右边
		//兄弟节点也设置为null
		if(leftMostNode.equals(subtree)){
			leftMostNode=leftMostNode.rightSiblingNode;
			unlinkParent(subtree);
			((LinkedMultiTreeNode<T>) subtree).rightSiblingNode=null;
			return true;
		}else{
			//否则就在剩下的下一代节点中，从左到右，一个个排除，找出符合指定参数相等的节点
			//如果找到了，那么就把它从父节点中剥离出来，且将它的右边节点设置为null，移除后，剩下的右边的节点往左边靠拢
			LinkedMultiTreeNode<T> nextSubtree=leftMostNode;
			while(nextSubtree.rightSiblingNode!=null){
				if(nextSubtree.rightSiblingNode.equals(subtree)){
					unlinkParent(subtree);
					nextSubtree.rightSiblingNode=nextSubtree.rightSiblingNode.rightSiblingNode;
					((LinkedMultiTreeNode<T>)subtree).rightSiblingNode=null;
					return true;
				} else{
					//如果已经到了最右边，那么直接将它复制过来。
					nextSubtree=nextSubtree.rightSiblingNode;
				}
			}
			
		}
		
		return false;
	}

	/**
	 * 
	 * 移除所有的子节点，和他们的所有后代，从当前节点
	 * 
	 */
	
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		//如果是枝叶节点，不需要做任何动作，否则执行下面的动作。
		if(!isLeaf()){
			// 找出最左边的下代节点。如果它的值不是null，从父节点中删除剥离出来。将所有的右边的节点，左移
			LinkedMultiTreeNode<T> nextNode=leftMostNode;
			while(nextNode!=null){
				unlinkParent(nextNode);
				LinkedMultiTreeNode<T> nextNodeRightSlibingNode=nextNode.rightSiblingNode;
				nextNode.rightSiblingNode=null;
				nextNode.lastSubtreeNode=null;
				nextNode=nextNodeRightSlibingNode;
			}
			leftMostNode=null;
		}
	}

	/**
	 * 
	 * 将当前树，返回一个iterator集合，
	 * 
	 */
	
	@Override
	public TreeNode<T>.TreeNodeIterator iterator() {
		// TODO Auto-generated method stub
		
		return new TreeNodeIterator(){

			/**
			 * 如果当前节点不是枝叶，那么返回当前节点的最左边的下一代节点 
			 */
			
			@Override
			protected TreeNode<T> leftMostNode() {
				// TODO Auto-generated method stub
				return leftMostNode;
			}

			/**
			 * 如果当前节点不是root，那么返回当前节点的右边的兄弟节点。
			 * 
			 */
			
			@Override
			protected TreeNode<T> rightSiblingNode() {
				// TODO Auto-generated method stub
				return rightSiblingNode;
			}
			
		};
	}
	
	/**
	 * 查看当前节点是否是枝叶节点，意思是它没有任何子节点，就是左边的的子节点为null
	 * 
	 */
	
	@Override
	public boolean isLeaf(){
		return leftMostNode==null;
	}
	
	
	/**
	 * 检测当前节点有没有指定参数的节点的后代。
	 * 
	 */
	
	@Override
	public boolean hasSubtree(TreeNode<T> subtree){
		// 如果指定参数为null，或者为枝叶，或者是root，那么就返回false
		if(subtree==null||isLeaf()||subtree.isRoot()){
			return false;
		}
		
		//将最左边的下一代节点找出来，如果它符合指定节点的要求，那么就返回true，否则向右一个个找。
		LinkedMultiTreeNode<T> nextSubtree=leftMostNode;
		while(nextSubtree!=null){
			if(nextSubtree.equals(subtree)){
				return true;
			}else{
				nextSubtree=nextSubtree.rightSiblingNode;
			}
		}
		return false;
	}
	
	/**
	 * 检测当前节点，和它的后代，是否含有指定的参数节点。
	 * 
	 */
	
	@Override
	public boolean contains(TreeNode<T> node){
		if(node==null||isLeaf()||node.isRoot()){
			return false;
		}
		LinkedMultiTreeNode<T> nextSubtree=leftMostNode;
		while(nextSubtree!=null){
			if(nextSubtree.equals(node)){
				return true;
			}
			if(nextSubtree.contains(node)){
				return true;
			}
			nextSubtree=nextSubtree.rightSiblingNode;
		}
		return false;
	}
	
	/**
	 * 移除当前节点中，第一个满足指定节点要求的，节点，从当前节点开始，使用pre order 方法预览树结构
	 * 
	 */
	
	@Override
	public boolean remove(TreeNode<T> node){
		if(node==null||isLeaf()||node.isRoot()){
			return false;
		}
		if(dropSubtree(node)){
			return true;
		}
		LinkedMultiTreeNode<T> nextSubtree=leftMostNode;
		while(nextSubtree!=null){
			if(nextSubtree.remove(node)){
				return true;
			}
			nextSubtree=nextSubtree.rightSiblingNode;
		}
		return false;
	}
	
	@Override
	public void traversePreOrder(TraversalAction<TreeNode<T>> action){
		if(!action.isCompleted()){
			action.perform(this);
			if(!isLeaf()){
				LinkedMultiTreeNode<T> nextNode=leftMostNode;
				while(nextNode!=null){
					nextNode.traversePreOrder(action);
					nextNode=nextNode.rightSiblingNode;
				}
			}
		}
	}
	
	
	@Override
	public void traversePostOrder(TraversalAction<TreeNode<T>> action){
		if(!action.isCompleted()){
			if(!isLeaf()){
				LinkedMultiTreeNode<T> nextNode = leftMostNode;
				while(nextNode!=null){
					nextNode.traversePostOrder(action);
					nextNode=nextNode.rightSiblingNode;
				}
			}
			action.perform(this);
		}
	}
	
	/**
	 * 计算当前节点，距离最底层的枝叶，有多高（最长的路径），中间隔了多少层。
	 */
	
	@Override
	public int height(){
		if(isLeaf()){
			return 0;
		}
		int height=0;
		LinkedMultiTreeNode<T> nextNode=leftMostNode;
		while(nextNode!=null){
			height=Math.max(height, nextNode.height());
			nextNode=nextNode.rightSiblingNode;
		}
		return height+1;
	}
	
	/**
	 * 
	 * 返回当前节点的兄弟节点的集合
	 * 
	 */
	
	@Override
	public Collection<? extends MultiTreeNode<T>> siblings(){
		if(isRoot()){
			String message=String.format("不能找到兄弟节点，因为当前节点 %1$s 是root", root());
			throw new TreeNodeException(message);
		}
		//找出当前节点的父节点，然后再找出它的下面的最左边的节点。
		LinkedMultiTreeNode<T> firstNode=((LinkedMultiTreeNode<T>) parent()).leftMostNode;
		if (firstNode.rightSiblingNode == null) {
			return Collections.emptySet();
		}
		Collection<MultiTreeNode<T>> siblings = new HashSet<>();
		LinkedMultiTreeNode<T> nextNode = firstNode;
		while (nextNode != null) {
			if (!nextNode.equals(this)) {
				siblings.add(nextNode);
			}
			nextNode = nextNode.rightSiblingNode;
		}
		return siblings;
	}
	

}
