#!/bin/bash
set -e

gradle clean && gradle -PbuildProfile=dev build && gradle -PbuildProfile=qa build
