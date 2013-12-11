/*
 * Copyright (c) 2004-2013 Universidade do Porto - Faculdade de Engenharia
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
 * Version 1.1 only (the "Licence"), appearing in the file LICENCE.md
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
 * Author: 
 * 20??/??/??
 */
package pt.lsts.neptus.util.llf.chart;

import java.io.File;
import java.util.Vector;

import pt.lsts.neptus.mra.MRAPanel;
import pt.lsts.neptus.mra.importers.IMraLogGroup;
import pt.lsts.neptus.mra.plots.CtdPlot;
import pt.lsts.neptus.mra.plots.EstimatedStatePlot;
import pt.lsts.neptus.mra.plots.EulerAnglesPlot;
import pt.lsts.neptus.mra.plots.LblRangesPlot;
import pt.lsts.neptus.mra.plots.SalinityVsDepthPlot;
import pt.lsts.neptus.mra.plots.ScriptedPlot;
import pt.lsts.neptus.mra.plots.StatisticsPlot;
import pt.lsts.neptus.mra.plots.TemperatureVsDepthPlot;
import pt.lsts.neptus.mra.plots.TidePlot;
import pt.lsts.neptus.mra.plots.VehicleGanttPlot;
import pt.lsts.neptus.mra.plots.XYPlot;
import pt.lsts.neptus.mra.plots.ZPlot;
import pt.lsts.neptus.mra.visualizations.MRAVisualization;
import pt.lsts.neptus.plugins.actualstate.ActualPosition;

/**
 * @author ZP
 */
public class MraChartFactory {

    private static Class<?>[] automaticCharts = new Class<?>[] { EstimatedStatePlot.class, XYPlot.class, ZPlot.class,
            LblRangesPlot.class, EulerAnglesPlot.class, CtdPlot.class, TemperatureVsDepthPlot.class, SalinityVsDepthPlot.class, VehicleGanttPlot.class, TidePlot.class, ActualPosition.class, StatisticsPlot.class
    };

    /**
     * Given a LLFSource, returns all predefined charts that can be applied to that source
     * 
     * @param source An LLFSource
     * @return A list of LLFCharts that can be successfully applied to the given source
     */
    public static MRAVisualization[] getAutomaticCharts(MRAPanel panel) {
        Vector<MRAVisualization> charts = new Vector<MRAVisualization>();

        
        for (int i = 0; i < automaticCharts.length; i++) {
            MRAVisualization chart;
            try {
                chart = (MRAVisualization) automaticCharts[i].getConstructor(MRAPanel.class).newInstance(panel);
                charts.add(chart);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        Vector<ScriptedPlot> scrptPlots = getScriptedPlots(panel);
        if (scrptPlots != null && scrptPlots.size() > 0)
            charts.addAll(scrptPlots);
        
        return charts.toArray(new MRAVisualization[charts.size()]);
    }
    
    public static Vector<ScriptedPlot> getScriptedPlots(MRAPanel panel) {
        Vector<ScriptedPlot> plots = new Vector<ScriptedPlot>();

        File sFx = new File("conf/mraplots");
        File[] scripts = sFx.exists() ? sFx.listFiles() : null;
        if (scripts == null || scripts.length == 0)
            return plots;
            
        for (File f : scripts) {
            if (f.isDirectory() || !f.canRead())
                continue;
            ScriptedPlot plot = new ScriptedPlot(panel, f.getAbsolutePath());
            plots.add(plot);
        }
        
        return plots;
    }

    /**
     * 
     * @param title
     * @param source
     * @param fieldsToPlot
     * @return
     */
    public static LLFChart createTimeSeriesChart(String title, IMraLogGroup source, String[] fieldsToPlot) {
        return null;
    }
}