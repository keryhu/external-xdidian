package com.xdidian.keryhu.tree;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This interface represents the basic tree data structure
 * <h1>Definition</h1>
 * A tree data structure can be defined recursively (locally) as a collection of nodes
 * (starting at a root node), where each node is a data structure consisting of a value,
 * together with a list of references to nodes (the children), with the constraints that
 * no reference is duplicated, and none points to the root
 * <p>
 * A tree is a (possibly non-linear) data structure made up of nodes or vertices and edges
 * without having any cycle. The tree with no nodes is called the <b>null</b> or
 * <b>empty</b> tree. A tree that is not empty consists of a root node and potentially many
 * levels of additional nodes that form a hierarchy
 * <h1>Terminology</h1>
 * <ul>
 *     <li><b>Node</b> - a single point of a tree</li>
 *     <li><b>Edge</b> - line, which connects two distinct nodes</li>
 *     <li><b>Root</b> - top node of the tree, which has no parent</li>
 *     <li><b>Parent</b> - a node, other than the root, which is connected to other successor
 *                         nodes</li>
 *     <li><b>Child</b> - a node, other than the root, which is connected to predecessor</li>
 *     <li><b>Leaf</b> - a node without children</li>
 *     <li><b>Path</b> - a sequence of nodes and edges connecting a node with a
 *                       descendant</li>
 *     <li><b>Path Length</b> - number of nodes in the path - 1</li>
 *     <li><b>Ancestor</b> - the top parent node of the path</li>
 *     <li><b>Descendant</b> - the bottom child node of the path</li>
 *     <li><b>Siblings</b> - nodes, which have the same parent</li>
 *     <li><b>Subtree</b> - a node in a tree with all of its proper descendants, if any</li>
 *     <li><b>Node Height</b> - the number of edges on the longest downward path between that
 *                              node and a leaf</li>
 *     <li><b>Tree Height</b> - the number of edges on the longest downward path between the
 *                              root and a leaf (root height)</li>
 *     <li><b>Depth (Level)</b> - the path length between the root and the current node</li>
 *     <li><b>Ordered Tree</b> - tree in which nodes has the children ordered</li>
 *     <li><b>Labeled Tree</b> - tree in which a label or value is associated with each node
 *                               of the tree</li>
 *     <li><b>Expression Tree</b> - tree which specifies the association of an expression�s
 *                                  operands and its operators in a uniform way, regardless
 *                                  of whether the association is required by the placement
 *                                  of parentheses in the expression or by the precedence and
 *                                  associativity rules for the operators involved</li>
 *     <li><b>Branching Factor</b> - maximum number of children a node can have</li>
 *     <li><b>Pre order</b> - a form of tree traversal, where the action is called firstly on
 *                           the current node, and then the pre order function is called again
 *                           recursively on each of the subtree from left to right</li>
 *     <li><b>Post order</b> - a form of tree traversal, where the post order function is called
 *                            recursively on each subtree from left to right and then the
 *                            action is called</li>
 * </ul>
 *
 */
public abstract class TreeNode<T> implements Iterable<TreeNode<T>>, Serializable, Cloneable {

	 
	private static final long serialVersionUID = -5956652432089159295L;

	
	private static final AtomicLong ID_GENERATOR = new AtomicLong(0);

	/**
	 * 用于区别不同的node 的id
	 */
	private final long id = ID_GENERATOR.getAndIncrement();

	
	protected TreeNode<T> parent;

	/**
	 * Data store in the current tree node
	 */
	protected T data;

	/**
	 * Creates an instance of this class
	 */
	
	public TreeNode(T data) {
		this.data = data;
	}

	/**
	 * Creates an instance of this class without setting the {@link #data}
	 */
	public TreeNode() {
	}

	/**
	 * 返回当前节点的所有后代。
	 * <p>
	 * Returns {@link Collections#emptySet()} if the current node is leaf
	 *
	 */
	
	public abstract Collection<? extends TreeNode<T>> subtrees();

	/**
	 * 增加后代到此节点下面
	 * 
	 */
	public abstract boolean add(TreeNode<T> subtree);

	/**
	 * Drops the first occurrence of the specified subtree from the current
	 * tree node
	 * <p>
	 * 如果移除成功，则返回true
	 *
	 */
	
	public abstract boolean dropSubtree(TreeNode<T> subtree);

	/**
	 * clear清空
	 */
	
	public abstract void clear();

	/**
	 * Returns an iterator over the elements in this tree in proper sequence
	 * 返回一个序列集合体
	 * 
	 */
	public abstract TreeNodeIterator iterator();

	/**
	 * 
	 * 返回存储在当前节点的数据。
	 *
	 */
	
	public T data() {
		return data;
	}

	/**
	 * 将此数据存储到当前节点
	 *
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 检测当前节点是否是根节点
	 *
	 *
	 */
	public boolean isRoot() {
		return parent == null;
	}

	/**
	 * Returns the root node of the current node
	 * 返回此tree 的根节点
	 * 
	 */
	public TreeNode<T> root() {
		if (isRoot()) {
			return this;
		}
		TreeNode<T> node = this;
		do {
			node = node.parent();
		} while (!node.isRoot());
		return node;
	}

	/**
	 * Returns the parent node of the current node
	 * 返回当前节点的父节点
	 * 
	 */
	
	public TreeNode<T> parent() {
		return parent;
	}

	/**
	 * 检测当前节点是否是最末端的枝叶，也就是没有下节点了
	 *
	 * 
	 */
	
	public boolean isLeaf() {
		return subtrees().isEmpty();
	}

	/**
	 * 检测当前tree是否拥有指定的是node 数据，如果有就返回第一个找到的此节点，如果没有则返回null
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	public TreeNode<T> find(final T data) {
		if (isLeaf()) {
			return (data() == null ? data == null : data().equals(data)) ? this : null;
		}
		final TreeNode<T>[] searchedNode = (TreeNode<T>[]) Array.newInstance(getClass(), 1);
		traversePreOrder(new TraversalAction<TreeNode<T>>() {
			@Override
			public void perform(TreeNode<T> node) {
				if ((node.data() == null ?
						data == null : node.data().equals(data))) {
					searchedNode[0] = node;
				}
			}

			@Override
			public boolean isCompleted() {
				return searchedNode[0] != null;
			}
		});
		return searchedNode[0];
	}

	/**
	 * 返回此tree，拥有此node 的 所有 节点。
	 * 
	 */
	
	public Collection<? extends TreeNode<T>> findAll(final T data) {
		if (isLeaf()) {
			return (data() == null ? data == null : data().equals(data)) ?
					Collections.singleton(this) : Collections.<TreeNode<T>>emptySet();
		}
		final Collection<TreeNode<T>> searchedNodes = new HashSet<>();
		traversePreOrder(new TraversalAction<TreeNode<T>>() {
			@Override
			public void perform(TreeNode<T> node) {
				if ((node.data() == null ?
						data == null : node.data().equals(data))) {
					searchedNodes.add(node);
				}
			}

			@Override
			public boolean isCompleted() {
				return false;
			}
		});
		return searchedNodes;
	}

	/**
	 * 检测当前节点是否拥有参数中的子节点。
	 * 
	 */
	
	public boolean hasSubtree(TreeNode<T> subtree) {
		if (subtree == null
				|| isLeaf()
				|| subtree.isRoot()) {
			return false;
		}
		for (TreeNode<T> mSubtree : subtrees()) {
			if (mSubtree.equals(subtree)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检测当前节点包含子节点，是否拥有参数中的节点对象。
	 *
	 */
	public boolean contains(TreeNode<T> node) {
		if (node == null
				|| isLeaf()
				|| node.isRoot()) {
			return false;
		}
		for (TreeNode<T> subtree : subtrees()) {
			if (subtree.equals(node)
					|| subtree.contains(node)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检测当前节点和后代，是否拥有参数中 节点集合，需要全部满足。
	 * 
	 */
	
	public boolean containsAll(Collection<TreeNode<T>> nodes) {
		if (isLeaf()
				|| areAllNulls(nodes)) {
			return false;
		}
		for (TreeNode<T> node : nodes) {
			if (!contains(node)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 从当前节点中往下，删除一个满足要求的参数中的节点，如果删除成功，则返回true，否则false
	 */
	public boolean remove(TreeNode<T> node) {
		if (node == null
				|| isLeaf()
				|| node.isRoot()) {
			return false;
		}
		if (dropSubtree(node)) {
			return true;
		}
		for (TreeNode<T> subtree : subtrees()) {
			if (subtree.remove(node)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 从当前节点到所有的后代，移除参数中的节点。如果移除成功，那么返回true
	 */
	
	public boolean removeAll(Collection<TreeNode<T>> nodes) {
		if (isLeaf()
				|| areAllNulls(nodes)) {
			return false;
		}
		boolean result = false;
		for (TreeNode<T> node : nodes) {
			boolean currentResult = remove(node);
			if (!result && currentResult) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 从当前节点开始向下，预览整个tree
	 *
	 */
	public void traversePreOrder(TraversalAction<TreeNode<T>> action) {
		if (!action.isCompleted()) {
			action.perform(this);
			if (!isLeaf()) {
				for (TreeNode<T> subtree : subtrees()) {
					subtree.traversePreOrder(action);
				}
			}
		}
	}

	/**
	 * 从当前节点开始，向上预览整个tree
	 *
	 */
	public void traversePostOrder(TraversalAction<TreeNode<T>> action) {
		if (!action.isCompleted()) {
			if (!isLeaf()) {
				for (TreeNode<T> subtree : subtrees()) {
					subtree.traversePostOrder(action);
				}
			}
			action.perform(this);
		}
	}

	/**
	 * 返回一个node节点的collections，从根节点向下方法。
	 *
	 */
	public Collection<TreeNode<T>> preOrdered() {
		if (isLeaf()) {
			return Collections.singleton(this);
		}
		final Collection<TreeNode<T>> mPreOrdered = new ArrayList<>();
		TraversalAction<TreeNode<T>> action = populateAction(mPreOrdered);
		traversePreOrder(action);
		return mPreOrdered;
	}

	/**
	 *返回所有节点的collections，顺序是反方向，从下而上。最后根节点。
	 */
	public Collection<TreeNode<T>> postOrdered() {
		if (isLeaf()) {
			return Collections.singleton(this);
		}
		final Collection<TreeNode<T>> mPostOrdered = new ArrayList<>();
		TraversalAction<TreeNode<T>> action = populateAction(mPostOrdered);
		traversePostOrder(action);
		return mPostOrdered;
	}

	/**
	 * 返回链接当前节点的所有path
	 *
	 */
	public Collection<? extends TreeNode<T>> path(TreeNode<T> descendant) {
		if (descendant == null
				|| isLeaf()
				|| this.equals(descendant)) {
			return Collections.singletonList(this);
		}
		String errorMessage = "不能找到连接当前节点的路线. ";
		if (descendant.isRoot()) {
			String message = String.format(errorMessage + "当前节点 %1$s 是根节点", descendant);
			throw new TreeNodeException(message);
		}
		List<TreeNode<T>> path = new LinkedList<>();
		TreeNode<T> node = descendant;
		path.add(node);
		do {
			node = node.parent();
			path.add(0, node);
			if (this.equals(node)) {
				return path;
			}
		} while (!node.isRoot());
		String message = String.format(errorMessage +
				"The specified tree node %1$s is not the descendant of tree node %2$s", descendant, this);
		throw new TreeNodeException(message);
	}

	/**
	 * 返回当前节点或指定节点的父节点。
	 *
	 */
	public TreeNode<T> commonAncestor(TreeNode<T> node) {
		String errorMessage = "不能找到当前节点的父节点. ";
		if (node == null) {
			String message = errorMessage + "传递的参数为空";
			throw new TreeNodeException(message);
		}
		if (!this.root().contains(node)) {
			String message = String.format(errorMessage +
					"The specified tree node %1$s was not found in the current tree node %2$s", node, this);
			throw new TreeNodeException(message);
		}
		if (this.isRoot()
				|| node.isRoot()) {
			String message = String.format(errorMessage + "The tree node %1$s is root", this.isRoot() ? this : node);
			throw new TreeNodeException(message);
		}
		if (this.equals(node)
				|| node.isSiblingOf(this)) {
			return parent();
		}
		int thisNodeLevel = this.level();
		int thatNodeLevel = node.level();
		return thisNodeLevel > thatNodeLevel ? node.parent() : this.parent();
	}

	/**
	 * 指出参数中的节点，是否是当前节点的兄弟节点
	 */
	
	public boolean isSiblingOf(TreeNode<T> node) {
		return node != null
				&& !isRoot()
				&& !node.isRoot()
				&& this.parent().equals(node.parent());
	}

	/**
	 * 指出当前节点是否是参数中节点的父节点。
	 */
	
	public boolean isAncestorOf(TreeNode<T> node) {
		if (node == null
				|| isLeaf()
				|| node.isRoot()
				|| this.equals(node)) {
			return false;
		}
		TreeNode<T> mNode = node;
		do {
			mNode = mNode.parent();
			if (this.equals(mNode)) {
				return true;
			}
		} while (!mNode.isRoot());
		return false;
	}

	/**
	 * 指出当前节点是否是参数中节点的后代。
	 *
	 */
	public boolean isDescendantOf(TreeNode<T> node) {
		if (node == null
				|| this.isRoot()
				|| node.isLeaf()
				|| this.equals(node)) {
			return false;
		}
		TreeNode<T> mNode = this;
		do {
			mNode = mNode.parent();
			if (node.equals(mNode)) {
				return true;
			}
		} while (!mNode.isRoot());
		return false;
	}

	/**
	 *返回当前节点往下还有共有多少个节点，包含当前节点自己。
	 *
	 */
	public long size() {
		if (isLeaf()) {
			return 1;
		}
		final long[] count = {0};
		TraversalAction<TreeNode<T>> action = new TraversalAction<TreeNode<T>>() {
			@Override
			public void perform(TreeNode<T> node) {
				count[0]++;
			}

			@Override
			public boolean isCompleted() {
				return false;
			}
		};
		traversePreOrder(action);
		return count[0];
	}

	/**
	 * 返回当前节点距离末端枝叶节点，的高度。
	 */
	public int height() {
		if (isLeaf()) {
			return 0;
		}
		int height = 0;
		for (TreeNode<T> subtree : subtrees()) {
			height = Math.max(height, subtree.height());
		}
		return height + 1;
	}

	/**
	 * 返回当前节点在整个tree中第几层。
	 */
	
	public int level() {
		if (isRoot()) {
			return 0;
		}
		int level = 0;
		TreeNode<T> node = this;
		do {
			node = node.parent();
			level++;
		} while (!node.isRoot());
		return level;
	}

	/**
	 * 放当前node，复制一个对象出去。
	 */
	@SuppressWarnings("unchecked")
	@Override
	public TreeNode<T> clone() {
		try {
			return (TreeNode<T>) super.clone();
		} catch (CloneNotSupportedException e) {
			String message = "Unable to clone the current tree node";
			throw new TreeNodeException(message, e);
		}
	}

	/**
	 * 指出参数中的对象是否和当前节点想等。
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null
				|| getClass() != obj.getClass()) {
			return false;
		}
		TreeNode<T> that = (TreeNode<T>) obj;
		return this.id == that.id;
	}

	
	@Override
	public int hashCode() {
		return (int) (this.id ^ (this.id >>> 32));
	}

	/**
	 * Returns the string representation of this object
	 *
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("\n");
		final int topNodeLevel = level();
		TraversalAction<TreeNode<T>> action = new TraversalAction<TreeNode<T>>() {
			@Override
			public void perform(TreeNode<T> node) {
				int nodeLevel = node.level() - topNodeLevel;
				for (int i = 0; i < nodeLevel; i++) {
					builder.append("|  ");
				}
				builder
						.append("+- ")
						.append(node.data())
						.append("\n");
			}

			@Override
			public boolean isCompleted() {
				return false;
			}
		};
		traversePreOrder(action);
		return builder.toString();
	}

	/**
	 * Populates the input collection with the tree nodes, while traversing the tree
	 *
	 * @param collection input collection to populate
	 * @param <T> type of the tree node
	 * @return traversal action, which populates the input collection with the tree nodes
	 */
	protected static <T> TraversalAction<TreeNode<T>> populateAction(final Collection<TreeNode<T>> collection) {
		return new TraversalAction<TreeNode<T>>() {
			@Override
			public void perform(TreeNode<T> node) {
				collection.add(node);
			}

			@Override
			public boolean isCompleted() {
				return false;
			}
		};
	}

	/**
	 * 将指定节点，和父节点，串起来
	 *
	 */
	protected static <T> void linkParent(TreeNode<T> node, TreeNode<T> parent) {
		if (node != null) {
			node.parent = parent;
		}
	}

	/**
	 * 将指定节点和他的父节点中移除
	 *
	 */
	protected static <T> void unlinkParent(TreeNode<T> node) {
		node.parent = null;
	}

	/**
	 * Checks whether there is at least one not {@code null} element within
	 * the input collection
	 * <pre>
	 *     Validator.isAnyNotNull(Arrays.asList("foo", null))   = true
	 *     Validator.isAnyNotNull(null)                         = false
	 *     Validator.isAnyNotNull(Collections.emptyList())      = false
	 *     Validator.isAnyNotNull(Arrays.asList(null, null))    = false
	 * </pre>
	 *
	 * @param collection input collection to check
	 * @param <T> type of the data, which parametrises collection
	 * @return {@code true} if there is at least one not {@code null} element within
	 *         the input collection; {@code false} otherwise
	 */
	protected static <T> boolean isAnyNotNull(Collection<T> collection) {
		if (collection == null || collection.isEmpty()) {
			return false;
		}
		for (T item : collection) {
			if (item != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether the specified collection is @{code null}, empty or if
	 * all of its elements are {@code null}
	 * <pre>
	 *     areAllNulls(null)                          = true
	 *     areAllNulls(Collections.emptyList())       = true
	 *     areAllNulls(Arrays.asList(null, null))     = true
	 *     areAllNulls(Arrays.asList("foo", null))    = false
	 * </pre>
	 *
	 * @param collection input collection to check
	 * @param <T> type of the data, which parametrises collection
	 * @return {@code true} if the specified collection is {@code null}, empty
	 *         or if all of its elements are {@code null}; {@code false} otherwise
	 */
	protected static <T> boolean areAllNulls(Collection<T> collection) {
		return !isAnyNotNull(collection);
	}

	/**
	 * Base tree node iterator, which is expected to be extended by {@link TreeNode}
	 * subclasses in order to perform custom implementation and return it in
	 * {@link #iterator()}
	 */
	protected abstract class TreeNodeIterator implements Iterator<TreeNode<T>> {

		/**
		 * An expected size of the tree node required to check
		 * whether the tree node was changed during <b>foreach</b>
		 * iteration
		 */
		private long expectedSize = size();

		/**
		 * Reference to the current tree node within iteration
		 */
		private TreeNode<T> currentNode;

		/**
		 * Reference to the next tree node within iteration
		 */
		private TreeNode<T> nextNode = TreeNode.this;

		/**
		 * Indicates whether there is a next tree node available
		 * within iteration
		 */
		private boolean nextNodeAvailable = true;

		/**
		 * Returns the leftmost node of the current tree node if the
		 * current tree node is not a leaf
		 *
		 * @return leftmost node of the current tree node if the current
		 *         tree node is not a leaf
		 * @throws TreeNodeException an exception that is thrown in case
		 *                           if the current tree node is a leaf
		 */
		protected abstract TreeNode<T> leftMostNode();

		/**
		 * Returns the right sibling node of the current tree node if the
		 * current tree node is not root
		 *
		 * @return right sibling node of the current tree node if the current
		 *         tree node is not root
		 * @throws TreeNodeException an exception that may be thrown in case if
		 *                           the current tree node is root
		 */
		protected abstract TreeNode<T> rightSiblingNode();

		/**
		 * Checks whether the current tree node is not a leaf and returns the
		 * leftmost node from {@link #leftMostNode()}
		 *
		 * @return leftmost node of the current tree node if the current tree
		 *         node is not a leaf
		 * @throws TreeNodeException an exception that is thrown in case
		 *                           if the current tree node is a leaf
		 */
		private TreeNode<T> checkAndGetLeftMostNode() {
			if (isLeaf()) {
				throw new TreeNodeException("Leftmost node can't be obtained. Current tree node is a leaf");
			} else {
				return leftMostNode();
			}
		}

		/**
		 * Checks whether the current tree node is not root and returns the
		 * right sibling node from {@link #rightSiblingNode()}
		 *
		 * @return right sibling node of the current tree node if the current
		 *         tree node is not root
		 * @throws TreeNodeException an exception that may be thrown in case if
		 *                           the current tree node is root
		 */
		private TreeNode<T> checkAndGetRightSiblingNode() {
			if (isRoot()) {
				throw new TreeNodeException("Right sibling node can't be obtained. Current tree node is root");
			} else {
				return rightSiblingNode();
			}
		}

		/**
		 * Returns {@code true} if the iteration has more elements;
		 * otherwise returns {@code false}
		 *
		 * @return {@code true} if the iteration has more elements;
		 *         {@code false} otherwise
		 */
		@Override
		public boolean hasNext() {
			return nextNodeAvailable;
		}

		/**
		 * Returns the next element in the iteration
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iteration has no more elements
		 */
		@Override
		public TreeNode<T> next() {
			checkForConcurrentModification();
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			currentNode = nextNode;
			if (nextNode.isLeaf()) {
				if (nextNode.isRoot()) {
					nextNodeAvailable = false;
				} else {
					do {
						TreeNode<T> currentNode = nextNode;
						nextNode = nextNode.parent();
						if (currentNode.equals(TreeNode.this)) {
							nextNodeAvailable = false;
							break;
						}
						TreeNode<T> nextSibling = currentNode.iterator().checkAndGetRightSiblingNode();
						if (nextSibling != null) {
							nextNode = nextSibling;
							break;
						}
					} while (true);
				}
			} else {
				nextNode = nextNode.iterator().checkAndGetLeftMostNode();
			}
			return currentNode;
		}

		/**
		 * Checks whether tree node was changed during <b>foreach</b>
		 * iteration and throws {@link ConcurrentModificationException}
		 * exception if so
		 */
		private void checkForConcurrentModification() {
			if (expectedSize != size()) {
				throw new ConcurrentModificationException();
			}
		}

		/**
		 * Removes from the underlying tree the last element returned by this
		 * iterator (optional operation)
		 * <p>
		 * This method can be called only once per call to {@link #next}.
		 * The behavior of an iterator is unspecified if the underlying tree
		 * is modified while the iteration is in progress in any way other
		 * than by calling this method
		 *
		 * @throws IllegalStateException an exception that may be thrown in case
		 *                               if remove was performed without any
		 *                               iteration
		 * @throws TreeNodeException an exception that may be thrown in case if
		 *                           remove was performed on a root node
		 */
		@Override
		public void remove() {
			String errorMessage = "Failed to remove the tree node. ";
			if (!isIterationStarted()) {
				throw new IllegalStateException(errorMessage + "The iteration has not been performed yet");
			}
			if (currentNode.isRoot()) {
				String message = String.format(errorMessage + "The tree node %1$s is root", currentNode);
				throw new TreeNodeException(message);
			}
			if (currentNode.equals(TreeNode.this)) {
				throw new TreeNodeException(errorMessage + "The starting node can't be removed");
			}
			checkForConcurrentModification();
			TreeNode<T> currentNode = this.currentNode;
			while (true) {
				if (currentNode.isRoot()) {
					nextNodeAvailable = false;
					break;
				}
				TreeNode<T> rightSiblingNode = currentNode.iterator().checkAndGetRightSiblingNode();
				if (rightSiblingNode != null) {
					nextNode = rightSiblingNode;
					break;
				}
				currentNode = currentNode.parent;
			}
			TreeNode<T> parent = this.currentNode.parent();
			parent.dropSubtree(this.currentNode);
			this.currentNode = parent;
			expectedSize = size();
		}

		/**
		 * Returns whether iteration has been started
		 *
		 * @return {@code true} if iteration has been started; {@code false} otherwise
		 */
		private boolean isIterationStarted() {
			return currentNode != null;
		}

	}

}
