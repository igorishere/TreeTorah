package br.com.treetorah.igor.treetorah.model;

public class Reforestation {

    private long id;
    private String date;
    private String state;
    private int cutedTrees;
    private double volumeCutedTrees;
    private int treesForPlant;
    private double valueToPay;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCutedTrees() {
        return cutedTrees;
    }

    public void setCutedTrees(int cutedTrees) {
        this.cutedTrees = cutedTrees;
    }

    public double getVolumeCutedTrees() {
        return volumeCutedTrees;
    }

    public void setVolumeCutedTrees(double volumeCutedTrees) {
        this.volumeCutedTrees = volumeCutedTrees;
    }

    public int getTreesForPlant() {
        return treesForPlant;
    }

    public void setTreesForPlant(int treesForPlant) {
        this.treesForPlant = treesForPlant;
    }

    public double getValueToPay() {
        return valueToPay;
    }

    public void setValueToPay(double valueToPay) {
        this.valueToPay = valueToPay;
    }


}
