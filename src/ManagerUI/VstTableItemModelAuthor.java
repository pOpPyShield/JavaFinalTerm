package ManagerUI;

import ObjectZZ.Author;
import ObjectZZ.Book;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class VstTableItemModelAuthor extends AbstractTableModel {
    private List<Author> object;
    private String[] column;
    public VstTableItemModelAuthor(List<Author> object, String[] column) {
        this.object = object;
        this.column = column;
    }

    public void setObject(List<Author> object) {
        this.object = object;
    }

    @Override
    public int getRowCount() {
        return this.object.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    public String getColumnName(int col) {
        return column[col];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        Author bookSee = this.object.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = bookSee.getIdAuthor();
                break;
            case 1:
                value = bookSee.getNameOfAuthor();
                break;
            case 2:
                value = bookSee.displayDate();
                break;
            case 3:
                value = bookSee.getImage();
                break;
            case 4:
                value = bookSee.getIdSql();
                break;
        }
        return value;
    }

    public void addRow(Author object) {
        this.object.add(object);
    }

    public Author getObjectAt(int row) {return this.object.get(row);}
    public void refresh(List<Author> objects) {
        fireTableChanged(null);
    }
}
