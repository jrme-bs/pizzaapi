FROM ubuntu:latest
LABEL authors="jerom"

ENTRYPOINT ["top", "-b"]