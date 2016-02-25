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

import java.util.Objects;

/**
 *
 * @author Simon Berndt
 */
public class BasicTopic implements Topic {

    private final Topic parentTopic;
    private final String topicAsString;
    private final String leafSubTopic;

    public BasicTopic(Topic parent, String topic) {
        if (topic.indexOf(LEVEL_SEPERATOR) != -1) {
            throw new IllegalArgumentException("Topic-Level-Seperator is a prohibited Character");
        }
        this.parentTopic = parent;
        this.leafSubTopic = topic;
        this.topicAsString = parent.toString() + Topic.LEVEL_SEPERATOR + topic;
    }

    @Override
    public Topic getParent() {
        return parentTopic;
    }

    @Override
    public Topic createChild(String childName) {
        return new BasicTopic(this, childName);
    }

    @Override
    public Topic createSibling(String siblingName) {
        return new BasicTopic(parentTopic, siblingName);
    }

    @Override
    public Topic createAsChildOf(Topic parentTopic) {
        return new BasicTopic(parentTopic, topicAsString.substring(1));
    }

    @Override
    public String toString() {
        return topicAsString;
    }

    @Override
    public String getLeafSubTopic() {
        return leafSubTopic;
    }

    @Override
    public int hashCode() {
        return topicAsString.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasicTopic other = (BasicTopic) obj;
        return Objects.equals(this.topicAsString, other.topicAsString);
    }
    
    

}
