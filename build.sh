#!/bin/bash
set -e

gradle clean && gradle -PbuildProfile=dev build -x test && gradle -PbuildProfile=qa build -x test
