/*
 * Copyright 2013 (c) MuleSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.raml.yagi.framework.grammar.rule;


import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import org.raml.yagi.framework.nodes.Node;
import org.raml.yagi.framework.nodes.SimpleTypeNode;
import org.raml.yagi.framework.suggester.ParsingContext;
import org.raml.yagi.framework.suggester.Suggestion;

public class MaxLengthRule extends Rule
{
    private int maxLength;

    public MaxLengthRule(int maxLength)
    {
        this.maxLength = maxLength;
    }

    @Nonnull
    @Override
    public List<Suggestion> getSuggestions(Node node, ParsingContext context)
    {
        return Collections.emptyList();
    }

    @Override
    public boolean matches(@Nonnull Node node)
    {
        if (node instanceof SimpleTypeNode)
        {
            return ((SimpleTypeNode) node).getLiteralValue().length() <= maxLength;
        }
        return false;
    }

    @Override
    public Node apply(@Nonnull Node node)
    {
        if (!matches(node))
        {
            return ErrorNodeFactory.createInvalidMaxLength(maxLength);
        }
        return createNodeUsingFactory(node, ((SimpleTypeNode) node).getLiteralValue());
    }


    @Override
    public String getDescription()
    {
        return "Max length";
    }
}
