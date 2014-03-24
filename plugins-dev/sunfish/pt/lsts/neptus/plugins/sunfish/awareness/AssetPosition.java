/*
 * Copyright (c) 2004-2014 Universidade do Porto - Faculdade de Engenharia
 * Laboratório de Sistemas e Tecnologia Subaquática (LSTS)
 * All rights reserved.
 * Rua Dr. Roberto Frias s/n, sala I203, 4200-465 Porto, Portugal
 *
 * This file is part of Neptus, Command and Control Framework.
 *
 * Commercial Licence Usage
 * Licencees holding valid commercial Neptus licences may use this file
 * in accordance with the commercial licence agreement provided with the
 * Software or, alternatively, in accordance with the terms contained in a
 * written agreement between you and Universidade do Porto. For licensing
 * terms, conditions, and further information contact lsts@fe.up.pt.
 *
 * European Union Public Licence - EUPL v.1.1 Usage
 * Alternatively, this file may be used under the terms of the EUPL,
 * Version 1.1 only (the "Licence"), appearing in the file LICENSE.md
 * included in the packaging of this file. You may not use this work
 * except in compliance with the Licence. Unless required by applicable
 * law or agreed to in writing, software distributed under the Licence is
 * distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the Licence for the specific
 * language governing permissions and limitations at
 * https://www.lsts.pt/neptus/licence.
 *
 * For more information please see <http://lsts.fe.up.pt/neptus>.
 *
 * Author: zp
 * Mar 24, 2014
 */
package pt.lsts.neptus.plugins.sunfish.awareness;

import pt.lsts.neptus.types.coord.LocationType;

/**
 * @author zp
 *
 */
public class AssetPosition implements Comparable<AssetPosition> {
    private String assetName;
    private LocationType loc = null;
    private double yaw = Double.NaN;
    private double sog = Double.NaN;
    private long timestamp;
    private double accuracy = Double.NaN;
    private String source = "unknown";
    private String type = "Sensor";
    
    public AssetPosition(String asset, double latDegrees, double lonDegrees) {
        this.timestamp = System.currentTimeMillis();
        this.assetName = asset;
        this.loc = new LocationType(latDegrees, lonDegrees);
    }

    /**
     * @return the assetName
     */
    public String getAssetName() {
        return assetName;
    }
   
    /**
     * @return the loc
     */
    public LocationType getLoc() {
        return loc;
    }
    
    /**
     * @return the yaw
     */
    public double getYaw() {
        return yaw;
    }

    /**
     * @param yaw the yaw to set
     */
    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    /**
     * @return the sog
     */
    public double getSog() {
        return sog;
    }

    /**
     * @param sog the sog to set
     */
    public void setSog(double sog) {
        this.sog = sog;
    }

    /**
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the accuracy
     */
    public double getAccuracy() {
        return accuracy;
    }

    /**
     * @param accuracy the accuracy to set
     */
    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
    
    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    public long getAge() {
        return System.currentTimeMillis() - getTimestamp();
    }

    @Override
    public int compareTo(AssetPosition o) {
        return (int)(getTimestamp()/1000) - (int)(o.getTimestamp()/1000);
    }
    
    @Override
    public int hashCode() {
        return (assetName + "_" + (getTimestamp()/1000)).hashCode();
    }
}