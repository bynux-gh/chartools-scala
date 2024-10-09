# chartools-scala
My Kotlin CharTools program reimplemented in Scala

## What is this for?

`chartools` is a quick-and-dirty command line program for translating between
hexadecimal ASCII values and the equivalent characters. This can be used for
anything from sending "secret" messages to your nerdy friends to decoding
strings during reverse engineering, to pulling up a basic ASCII table.

## Wait, this is just one file!

Yep! Scala CLI made it *far* easier to put this all in one simple file than it
would've been to split it up. In fact, if you have
[Scala CLI](scala-cli.virtuslab.org/) or `scala >= 3.5.0`, you can simply run
the file as a script!

```sh
# Using scala 3.5.0 or newer:
scala CharTools.scala
# Using Scala CLI
scala-cli CharTools.scala
```

## I'd still prefer to compile and run the program.

No worries, you can pull the latest version from the
[Releases](https://github.com/bynux-gh/chartools-scala/releases)
section. The plain `chartools` program is for any platform on which
Java programs can run. `chartools-native` is currently only compatible
with Linux on AMD64/Intel x86_64 platforms, but is noticeably more
responsive and a third of the size.

## No, I want to compile it *myself*.

Excellent, for this you have two options. You must either have Scala
3.5.0 or newer, which uses Scala CLI as its backend (*recommended*),
or use Scala CLI. The remainder of this README will assume you're using
the former; replace `scala` with `scala-cli` for the following commands
if this is not the case.

### Compiling to JVM (for portability)

This can be done with one command:

```sh
scala --power package --assembly -o chartools CharTools.scala
# To run it:
./chartools
# You can then move the program to .local/bin or whatever directory you have
# set up for personal scripts/programs.
mv chartools ~/.local/bin/chartools
```

The `--assembly` command line option packages standard libraries by default so
that the program can be run entirely offline (instead of downloading the
libraries on first run, which is the default behavior of packages compiled by
Scala CLI).

### Compiling to Native code (for speed, via Scala Native)

Scala CLI (and therefore Scala 3.5.0+) has first-class support for compiling to
native code via [Scala Native](http://www.scala-native.org/). This locks the
program to your OS and architecture, but allows it to be a bit more responsive
since it no longer needs to spin up a JVM. This can be useful if you're using
`chartools` in scripts or just want to spend one less half-second translating
`64 65 65 7a 20 6e 75 74 73` into something legible.

**Note: Compiling with Scala Native requires [LLVM](https://llvm.org/) and
`clang` (usually packaged together) to be installed on your computer.** See
Scala Native's [environment setup](https://scala-native.org/en/stable/user/setup.html#installing-clang-and-runtime-dependencies)
page for more info.

```sh
scala --power package --native CharTools.scala
# To run it:
./chartools
# You can then move the program to .local/bin or whatever directory you have
# set up for personal scripts/programs.
mv chartools ~/.local/bin/chartools
```

## Okay, how do I use it?

### Interactive Mode

To start an interactive loop, simply run `chartools`.

```
$ chartools
(E)ncode, (D)ecode, (T)able, or (Q)uit?
>
```

From here, follow the prompts! Instructions are given step-by-step.

### Using command line arguments

`chartools` is set to interpret the following command line arguments:
- `d | decode`: Prompts for a HEX string separated by spaces and/or commas,
  and returns the equivalent ASCII/UTF-8 string.
- `e | encode`: Prompts for a regular string of characters and returns the
  hexadecimal representation of the string separated by spaces.
- `t | table`: Prints a quick-reference ASCII table covering 0x20 (space)
  to 0x7f (backspace) and quits.

The `decode` and `encode` options can also be followed by the desired string
to de-/encode, in which case the corresponding string will be printed to the
console and the program will quit immediately. This can be useful for scripts
or quick translations.
