package com.sale.swing.table;

import com.sale.model.ModelStudent;

public interface EventAction {

    public void delete(ModelStudent student);

    public void update(ModelStudent student);
}
