package com.pragma.presentation.view.fragment;

import android.app.Fragment;
import android.widget.Toast;

import com.pragma.presentation.internal.di.HasComponent;

/**
 * Created by anbu.ezhilan on 16/03/2017.
 */

public abstract class BaseFragment extends Fragment {
    /**
     * Shows a {@link android.widget.Toast} message
     * @param message An String representing a message to be shown
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }


}
