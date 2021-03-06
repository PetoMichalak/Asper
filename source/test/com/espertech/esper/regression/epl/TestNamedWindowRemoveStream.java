/*
 * *************************************************************************************
 *  Copyright (C) 2008 EsperTech, Inc. All rights reserved.                            *
 *  http://esper.codehaus.org                                                          *
 *  http://www.espertech.com                                                           *
 *  ---------------------------------------------------------------------------------- *
 *  The software in this package is published under the terms of the GPL license       *
 *  a copy of which has been included with this distribution in the license.txt file.  *
 * *************************************************************************************
 */

package com.espertech.esper.regression.epl;

import com.espertech.esper.client.scopetest.EPAssertionUtil;
import junit.framework.TestCase;
import com.espertech.esper.client.*;
import com.espertech.esper.support.bean.SupportBean;
import com.espertech.esper.support.client.SupportConfigFactory;

public class TestNamedWindowRemoveStream extends TestCase
{
    private EPServiceProvider epService;

    public void setUp()
    {
        Configuration config = SupportConfigFactory.getConfiguration();
        epService = EPServiceProviderManager.getDefaultProvider(config);
        epService.initialize();
    }

    public void testRemoveStream()
    {
        String[] fields = new String[] {"theString"};
        epService.getEPAdministrator().getConfiguration().addEventType("SupportBean", SupportBean.class);
        EPStatement stmt1 = epService.getEPAdministrator().createEPL("create window W1.win:length(2) as select * from SupportBean");
        EPStatement stmt2 = epService.getEPAdministrator().createEPL("create window W2.win:length(2) as select * from SupportBean");
        EPStatement stmt3 = epService.getEPAdministrator().createEPL("create window W3.win:length(2) as select * from SupportBean");
        epService.getEPAdministrator().createEPL("insert into W1 select * from SupportBean");
        epService.getEPAdministrator().createEPL("insert rstream into W2 select rstream * from W1");
        epService.getEPAdministrator().createEPL("insert rstream into W3 select rstream * from W2");

        epService.getEPRuntime().sendEvent(new SupportBean("E1", 1));
        epService.getEPRuntime().sendEvent(new SupportBean("E2", 1));
        EPAssertionUtil.assertPropsPerRowAnyOrder(stmt1.iterator(), fields, new Object[][]{{"E1"}, {"E2"}});
        
        epService.getEPRuntime().sendEvent(new SupportBean("E3", 1));
        EPAssertionUtil.assertPropsPerRowAnyOrder(stmt1.iterator(), fields, new Object[][]{{"E2"}, {"E3"}});
        EPAssertionUtil.assertPropsPerRowAnyOrder(stmt2.iterator(), fields, new Object[][]{{"E1"}});

        epService.getEPRuntime().sendEvent(new SupportBean("E4", 1));
        epService.getEPRuntime().sendEvent(new SupportBean("E5", 1));
        EPAssertionUtil.assertPropsPerRowAnyOrder(stmt1.iterator(), fields, new Object[][]{{"E4"}, {"E5"}});
        EPAssertionUtil.assertPropsPerRowAnyOrder(stmt2.iterator(), fields, new Object[][]{{"E2"}, {"E3"}});
        EPAssertionUtil.assertPropsPerRowAnyOrder(stmt3.iterator(), fields, new Object[][]{{"E1"}});
    }
}