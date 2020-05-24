using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TTS : MonoBehaviour
{
    public AudioSource readtext;

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.H))
        {
            if (readtext.isPlaying)
                readtext.Stop();
            else
                readtext.Play();
        }

    }

    public void hearwords()
    {
        if (readtext.isPlaying)
            readtext.Stop();
        else
            readtext.Play();
    }
}
