import NativeSnackbar, { type ShowProps } from '../specs/NativeSnackbar';

function show({ action, ...props }: SnackbarProps) {
  return NativeSnackbar.show(
    {
      actionText: action?.text,
      actionTextColor: action?.textColor,
      ...props,
    },
    action?.onPress
  );
}

export const Snackbar = {
  show,
};

export type SnackbarProps = Omit<
  ShowProps,
  'duration' | 'animationMode' | 'actionText' | 'actionTextColor'
> & {
  duration?: 'short' | 'long' | 'indefinite';
  animationMode?: 'slide' | 'fade';
  action?: {
    text: string;
    onPress?: () => void;
    textColor?: string;
  };
};
