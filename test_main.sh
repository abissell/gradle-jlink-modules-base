#!/bin/sh

application/build/image/bin/main -json '{"message":"Hello","receivers":["Lisa","John"]}' -debug
application/build/image/bin/java --list-modules
