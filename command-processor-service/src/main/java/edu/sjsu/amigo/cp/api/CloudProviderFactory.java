/*
 * Copyright (c) 2017 San Jose State University.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package edu.sjsu.amigo.cp.api;

import edu.sjsu.amigo.cp.azure.AzureCommandExecutor;
import edu.sjsu.amigo.cp.aws.AWSCommandExecutor;

/**
 * An abstract factory class for cloud providers.
 *
 * @author rwatsh on 2/15/17.
 */
public abstract class CloudProviderFactory {

    public static final String AWS = "aws";

    /**
     * Get the cloud provider specific executor class based on provider's name.
     *
     * @param providerName
     * @return
     */
    public static final CommandExecutor getCloudProviderCmdExecutor(String providerName) {
        if (providerName == null) return null;

        switch (providerName.toLowerCase()) {
            case "aws": return new AWSCommandExecutor();
            case "azure": return new AzureCommandExecutor();
            default: return null;
        }
    }
}
