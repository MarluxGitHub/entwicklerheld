export function getPascalsTriangleRow(rowNumber) {
    if (rowNumber === 0) {
        return [1];
    }
    if (rowNumber === 1) {
        return [1,1];
    }
    const previousRow = getPascalsTriangleRow(rowNumber - 1);
    const newRow = [1];
    for (let i = 0; i < previousRow.length - 1; i++) {
        newRow.push(previousRow[i] + previousRow[i + 1]);
    }
    newRow.push(1);
    return newRow;
}