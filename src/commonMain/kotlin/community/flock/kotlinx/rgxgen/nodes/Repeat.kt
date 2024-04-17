package community.flock.kotlinx.rgxgen.nodes

import community.flock.kotlinx.rgxgen.visitors.NodeVisitor

/* **************************************************************************
  Copyright 2019 Vladislavs Varslavans

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
/ * **************************************************************************/

class Repeat(pattern: String, val node: Node, val min: Int, val max: Int) : Node(pattern) {
    constructor(pattern: String, node: Node, times: Int) : this(pattern, node, times, times)

    override fun visit(visitor: NodeVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return "Repeat{" + node +
                ", aMin=" + min +
                ", aMax=" + max +
                '}'
    }

    companion object {
        @JvmStatic
        fun minimum(pattern: String, node: Node, times: Int): Repeat {
            return Repeat(pattern, node, times, -1)
        }
    }
}
