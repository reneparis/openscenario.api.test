/*
 * Copyright 2020 RA Consulting
 *
 * RA Consulting GmbH licenses this file under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


#include "MemLeakDetection.h"
#include <memory>
#include "TreeContentMessage.h"

namespace NET_ASAM_OPENSCENARIO
{
    TreeContentMessage::TreeContentMessage(const std::string message, const ErrorLevel errorLevel, std::shared_ptr<ITreeContext> treeContext):ContentMessage(message, errorLevel), _treeContext(treeContext) {}
        
    std::shared_ptr<ITreeContext> TreeContentMessage::GetTreeContext()
    {
        return _treeContext;
    }
}
