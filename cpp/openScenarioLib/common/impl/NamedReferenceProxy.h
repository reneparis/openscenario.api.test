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

#pragma once

#include "IOpenScenarioModelElement.h"
#include "INamedReference.h"
#include <string>
#include "MemLeakDetection.h"

/**
 * A generic implementation of INamedReference
 *
*/
namespace NET_ASAM_OPENSCENARIO
{
    template <class T>
    class NamedReferenceProxy:public INamedReference<T>
    {
    private:
        std::shared_ptr<T> _targetObject;
        std::string _nameRef;
        std::weak_ptr<IOpenScenarioModelElement> _parent;


    public:
        NamedReferenceProxy() :_targetObject(nullptr), _nameRef("") {}

        /**
        * Constructor using both, target object and nameRef
        *
        * @param targetObject
        * @param nameRef
        */
        NamedReferenceProxy(std::shared_ptr<T>& targetObject, std::string& nameRef) :_targetObject(targetObject), _nameRef(nameRef) {}

        /**
         * Constructor using only the name that represents the target object
         *
         * @param nameRef name that represents the reference to a real object
         */
        NamedReferenceProxy(const std::string nameRef): _targetObject(nullptr),  _nameRef(nameRef) {}

        std::shared_ptr<T> GetTargetObject() override
         {
             return _targetObject;
         }

         /**
          * Sets the target object (resolving the reference)
          *
          * @param targetObject the target object
          */
         void SetTargetObject(std::shared_ptr<T>& targetObject)
         {
             _targetObject = targetObject;
         }

        std::string GetNameRef() override
         {
             return _nameRef;
         }

        /* copy construction from rhs (clone)*/

        NamedReferenceProxy(const NamedReferenceProxy& rhs):_targetObject(rhs._targetObject), _nameRef(rhs._nameRef), _parent(rhs._parent) {}

        std::shared_ptr<void> GetAdapter(const std::string classifier) override
        {
            // No adapter returned
            return nullptr;
        }

        std::weak_ptr<IOpenScenarioModelElement> GetParent() override
        {
            return _parent;
        }

        void SetParent(const std::weak_ptr<IOpenScenarioModelElement> parent) override
        {
            _parent = parent;
        }
    };
}