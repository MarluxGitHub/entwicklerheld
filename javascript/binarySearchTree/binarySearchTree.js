export function printTree(treeNode) {
    let nodes = Object.values(treeNode);
    if (nodes[1] != null) {
        nodes[1] = printTree(nodes[2]);
    }
    if (nodes[2] != null) {
        nodes[2] = printTree(nodes[2]);
    }
    return nodes;
}

export function checkTreeEqualness(treeNode1, treeNode2){
    return (printTree(treeNode1).toString() == printTree(treeNode2).toString()) ? true : false;

}

export function reverse(treeNode) {
    if (treeNode == null) {
        return null;
    }
    let temp = treeNode.left;
    treeNode.left = reverse(treeNode.right);
    treeNode.right = reverse(temp);
    return treeNode;
}
