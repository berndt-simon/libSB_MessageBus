/* 
 * The MIT License
 *
 * Copyright 2016 Simon Berndt.
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
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package libSB.bus.topic;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Simon Berndt
 */
class RootTopic implements Topic {

    private static final Logger LOG = Logger.getLogger(RootTopic.class.getName());

    RootTopic() {
    }

    @Override
    public Topic getParent() {
        LOG.log(Level.WARNING, "Root-Topic does not posses a Parent");
        return null;
    }

    @Override
    public Topic createChild(String childName) {
        return new BasicTopic(this, childName);
    }

    @Override
    public Topic createSibling(String siblingName) {
        throw new UnsupportedOperationException("Can't create a sibling of Root");
    }

    @Override
    public Topic createAsChildOf(Topic parentTopic) {
        LOG.log(Level.INFO, "Creating a Child from a Topic and Root-Topic is a useless Operation");
        return parentTopic;
    }

    @Override
    public String toString() {
        return String.valueOf(LEVEL_SEPERATOR);
    }

    @Override
    public String getLeafSubTopic() {
        return String.valueOf(LEVEL_SEPERATOR);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return Character.hashCode(LEVEL_SEPERATOR);
    }

}
