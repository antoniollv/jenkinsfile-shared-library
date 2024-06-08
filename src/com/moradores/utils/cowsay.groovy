//src/com/moradores/cowsay.groovy
package com.moradores

def call(String text) {
  def border = { chr ->
    chr * (text.length() + 2)
  }

  def cow = """
${border('_')}
< ${text} >
${border('-')}
       \\   ^__^
        \\  (oo)\\_______
           (__)\\       )\\/\\
               ||----w |
               ||     ||
"""
  println cow
}
