package com.novarch.jojomod.capabilities.timestop;

import net.minecraft.entity.Entity;

public interface ITimestop {
    Entity getEntity();
    double getPosX();
    double getPosY();
    double getPosZ();
    void setPosition(double posX, double posY, double posZ);
    double getMotionX();
    double getMotionY();
    double getMotionZ();
    void setMotion(double motionX, double motionY, double motionZ);
    float getRotationYaw();
    float getRotationPitch();
    float getRotationYawHead();
    void setRotation(float rotationYaw, float rotationPitch, float rotationYawHead);
    int getFuse();
    void setFuse(int fuse);
    void putPosX(double posX);
    void putPosY(double posY);
    void putPosZ(double posZ);
    void putMotionX(double motionX);
    void putMotionY(double motionY);
    void putMotionZ(double motionZ);
    void putRotationYaw(float rotationYaw);
    void putRotationPitch(float rotationPitch);
    void putRotationYawHead(float rotationYawHead);
    void putFuse(int fuse);
    void onDataUpdated();
    void clear();
}
