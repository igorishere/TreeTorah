package br.com.treetorah.igor.treetorah.model;

import android.provider.BaseColumns;

public  final class TableEstructure{

        private TableEstructure(){}

    public static class Table  implements BaseColumns {
        public static final String TABLE_NAME = "reflorestation";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_STATE = "state";
        public static final String COLUMN_CUTED_TREES = "cutedTrees";
        public static final String COLUMN_VOLUME_CUTED_TREES = "volumeCutedTrees";
        public static final String COLUMN_TREES_FOR_PLANT = "treesForPlant";
        public static final String COLUMN_VALUE_TO_PAY = "valueToPay";
    }
}
