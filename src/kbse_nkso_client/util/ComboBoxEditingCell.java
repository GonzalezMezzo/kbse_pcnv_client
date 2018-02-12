/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbse_nkso_client.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import kbse_nkso_client.access.dto.PostDTO;

/**
 * Override of TableCell to allow editing of cells in TableView with a ComboBox
 */
public class ComboBoxEditingCell extends TableCell<PostDTO, Integer> {

    private ComboBox<Integer> comboBox;

    /**
     *
     */
    public ComboBoxEditingCell() {
    }

    /**
     * Override of cancelEdit to render the usual TableCell instead of the
     * ComboBox in the event of a canceled Edit
     */
    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getInteger().toString());
        setGraphic(null);
    }
    
    /**
     * Override of startEdit to allow rendering a ComboBox on edit
     */
    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createComboBox();
            setText(null);
            setGraphic(comboBox);
        }
    }

    /**
     * Override of updateItem to set Selected ComboBox Value as the new Value of
     * the TableCell
     *
     * @param item the Displayed TableCellValue to be updated
     * @param empty
     */
    @Override
    public void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (comboBox != null) {
                    comboBox.setValue(getInteger());
                }
                setText(getInteger().toString());
                setGraphic(comboBox);
            } else {
                setText(getInteger().toString());
                setGraphic(null);
            }
        }
    }

    /**
     * Define rendering of the list of values in ComboBox
     * @param comboBox
     */
    private void comboBoxConverter(ComboBox<Integer> comboBox) {
        comboBox.setCellFactory((c) -> {
            return new ListCell<Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.toString());
                    }
                }
            };
        });
    }
    
    /**
     * creates new ComboBox whoose Values are defined in integerList
     */
    private void createComboBox() {
        ObservableList<Integer> integerList = FXCollections.observableArrayList(
                new Integer(1),
                new Integer(2),
                new Integer(3),
                new Integer(4),
                new Integer(5),
                new Integer(6),
                new Integer(7),
                new Integer(8),
                new Integer(9),
                new Integer(10));
        comboBox = new ComboBox<>(integerList);
        comboBoxConverter(comboBox);
        comboBox.valueProperty().set(getInteger());
        comboBox.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        comboBox.setOnAction((e) -> {
            commitEdit(comboBox.getSelectionModel().getSelectedItem());
        });
    }

    private Integer getInteger() {
        return getItem() == null ? new Integer("0") : getItem();
    }
}
