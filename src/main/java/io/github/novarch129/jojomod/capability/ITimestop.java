package io.github.novarch129.jojomod.capability;

import net.minecraft.entity.Entity;

import java.util.Map;

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

    float getFallDistance();

    void setFallDistance(float fallDistance);

    int getFuse();

    void setFuse(int fuse);

    int getFire();

    void setFire(int fire);

    int getAge();

    void setAge(int age);

    Map<String, Float> getDamage();

    void setDamage(Map<String, Float> damage);

    void putPosX(double posX);

    void putPosY(double posY);

    void putPosZ(double posZ);

    void putMotionX(double motionX);

    void putMotionY(double motionY);

    void putMotionZ(double motionZ);

    void putRotationYaw(float rotationYaw);

    void putRotationPitch(float rotationPitch);

    void putRotationYawHead(float rotationYawHead);

    void putFallDistance(float fallDistance);

    void putFuse(int fuse);

    void putFire(int fire);

    void putAge(int age);

    void putDamage(Map<String, Float> damage);

    void onDataUpdated();

    void clear();
}
