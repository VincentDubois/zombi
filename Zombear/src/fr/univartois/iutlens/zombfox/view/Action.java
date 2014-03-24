package fr.univartois.iutlens.zombfox.view;

public interface Action {

	public abstract int getIndex();

	public abstract void move();

	public abstract boolean isFini();

}