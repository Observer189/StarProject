package com.mygdx.game.utils;

/**
 * Created by Sash on 01.05.2018.
 */

public class Vector2D
{
    public float vX;
    public float vY;

    public Vector2D()
    {

        vX=1;
        vY=1;
    }
    public Vector2D(float x,float y)
    {

        vX=x;
        vY=y;
    }
    public Vector2D(Vector2D v)
    {

        this.vX=v.vX;
        this.vY=v.vY;

    }
    double length()//нахождение длины вектора
    {
        double leng=Math.sqrt(Math.pow(vX, 2)+Math.pow(vY, 2));
        return leng;
    }
    void add(Vector2D v)//сложение векторов
    {
        this.vX+=v.vX;
        this.vY+=v.vY;
    }
    void sub(Vector2D v)//вычитание из данного вектора вектора в параметре
    {
        this.vX-=v.vX;
        this.vY-=v.vY;
    }
    void scale(double scaleFactor)//умножение вектора на число
    {
        this.vX*=scaleFactor;
        this.vY*=scaleFactor;
    }
    void normalize()//нормализация вектора
    {
        double leng=this.length();
        this.vX/=leng;
        this.vY/=leng;

    }
    double dotProduct(Vector2D v)//скалярное произведение векторов
    {
        double dot=this.vX*v.vX+this.vY*v.vY;
        return dot;
    }

    void print()
    {
        System.out.print(vX);
        System.out.println(vY);
    }
}
