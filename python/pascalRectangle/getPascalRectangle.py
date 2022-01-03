def get_pascals_triangle_row(row_number: int) -> list:
    """
    Returns the row of Pascal's Triangle at the given row number.
    """
    row = [1]
    for i in range(row_number):
        row = [1] + [row[i] + row[i + 1] for i in range(len(row) - 1)] + [1]
    return row