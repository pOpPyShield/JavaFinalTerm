package UserUI;

import ObjectZZ.Book;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class VstTableItemModelUser extends AbstractTableModel {
    private List<Book> object;
    private String[] column;
    public VstTableItemModelUser(List<Book> object, String[] column) {
        this.object = object;
        this.column = column;
    }

    public void setObject(List<Book> object) {
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
        Book bookSee = this.object.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = bookSee.getIDBook();
                break;
            case 1:
                value = bookSee.getNameOfBook();
                break;
            case 2:
                value = bookSee.displayPrice();
                break;
            case 3:
                value = bookSee.getAuthor();
                break;
            case 4:
                value = bookSee.getType();
                break;
            case 5:
                value = bookSee.displayDate();
                break;
            case 6:
                value = bookSee.getImage();
                break;
            case 7:
                value = bookSee.getIdBookSQL();
                break;



        }
        return value;
    }

    public void addRow(Book object) {
        this.object.add(object);
    }

    public Book getObjectAt(int row) {return this.object.get(row);}
    public void refresh(List<Book> objects) {
        fireTableChanged(null);
    }

}
