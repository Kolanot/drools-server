/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.quicksign.drools.server;

import org.kie.api.KieServices;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.Arrays;

/**
 * This shows off a decision table.
 */
public class PricingRuleDTExample {

    public static final void main(String[] args) throws Exception {
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        System.out.println(kc.verify().getMessages().toString());
        execute( kc );
    }

    public static void execute( KieContainer kc ) throws Exception {
        StatelessKieSession ksession = kc.newStatelessKieSession( "default");

        //now create some test data
        FactType driverType = kc.getKieBase("default").getFactType( "io.quicksign.drools.server",
                "Driver" );
        FactType policyType = kc.getKieBase("default").getFactType( "io.quicksign.drools.server",
                "Policy" );
        Object driver = driverType.newInstance();
        Object policy = policyType.newInstance();

        ksession.execute( Arrays.asList( new Object[]{driver, policy} ) );

        System.out.println( "BASE PRICE IS: " + policyType.get(policy, "basePrice") );
        System.out.println( "DISCOUNT IS: " + policyType.get(policy, "discountPercent") );

    }

}