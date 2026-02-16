#!/bin/sh

application/build/image/bin/main -json '{"message":"Hello","receivers":["Lisa","John"]}' -debug
echo ''
application/build/image/bin/java --list-modules
