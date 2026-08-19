/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimiteCaracteres extends PlainDocument {
    private final int limite;

    public LimiteCaracteres(int limite) {
        this.limite = limite;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limite && str.matches("[0-9]+")) {
            super.insertString(offset, str, attr);
        }
    }
}