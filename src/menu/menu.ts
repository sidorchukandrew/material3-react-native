import type { ReactElement, ReactNode } from 'react';
import NativeMenu from '../specs/NativeMenu';
import React, { useRef } from 'react';
import { findNodeHandle } from 'react-native';

export type Gravity =
  | 'top'
  | 'bottom'
  | 'left'
  | 'right'
  | 'center'
  | 'center_vertical'
  | 'center_horizontal'
  | 'fill'
  | 'fill_vertical'
  | 'fill_horizontal'
  | 'start'
  | 'end'
  | 'clip_vertical'
  | 'clip_horizontal'
  | 'no_gravity';

function show(
  props: { id: number; gravity?: Gravity },
  items: Array<any>,
  callbacks: { [key: string]: () => void }
) {
  NativeMenu.show(props, items, (selectedId: number) => {
    callbacks[selectedId]?.();
  });
}

export interface MenuProps {
  children: ReactNode;
  gravity?: Gravity;
}

export function Menu({ children, gravity }: MenuProps) {
  let trigger: ReactElement | null = null;
  let items: ReactNode | null = null;
  const pressableRef = useRef();

  React.Children.forEach(children, (child) => {
    if (React.isValidElement(child)) {
      if (child.type === Menu.Trigger) {
        trigger = child;
      } else if (child.type === Menu.Items) {
        items = child;
      }
    }
  });

  if (!trigger) return children;
  const pressable = React.Children.toArray(
    (trigger as ReactElement).props?.children
  )?.[0];

  if (!React.isValidElement(pressable)) return children;

  function handleShow(e: any) {
    const idCounter = { current: 1 };
    const callbacks = {};

    if (!pressableRef.current) return console.warn('No ref found for trigger');

    const nativeId = findNodeHandle(pressableRef.current);
    if (!nativeId) return console.warn('No native id found for trigger');

    const itemsJSON = convertItemsToJSON(
      (items as ReactElement)?.props?.children,
      idCounter,
      callbacks
    );

    if (!itemsJSON) return console.warn('No items found for menu');
    show({ id: nativeId, gravity }, itemsJSON, callbacks);

    if (!pressable) return console.warn('No component found for trigger');
    (pressable as ReactElement).props?.onPress?.(e);
  }

  const pressableClone = React.cloneElement(pressable, {
    ref: pressableRef,
    onPress: handleShow,
  });

  return pressableClone;
}

interface MenuTriggerProps {
  children: ReactElement;
}
Menu.Trigger = function Trigger({ children }: MenuTriggerProps) {
  return children;
};

interface ItemsProps {
  children: ReactNode;
}

Menu.Items = function Items(_props: ItemsProps) {
  return null;
};

interface MenuItemProps {
  title: string;
  groupId?: number;
  onSelect: () => void;
  isCheckable?: boolean;
  isChecked?: boolean;
  icon?: string;
}

Menu.Item = function Item(_props: MenuItemProps) {
  return null;
};

interface MenuSubMenuProps {
  children: ReactNode;
  title: string;
  groupId?: number;
  icon?: string;
}

Menu.SubMenu = function SubMenu(_props: MenuSubMenuProps) {
  return null;
};

function convertItemsToJSON(
  items: ReactNode | null,
  idCounter: { current: number },
  callbacks: { [key: string]: () => void }
) {
  if (!items) {
    return null;
  }

  const itemsJSON: Array<any> = [];

  React.Children.forEach(items, (child) => {
    if (React.isValidElement(child)) {
      if (child.type === Menu.Item) {
        const itemId = idCounter.current++;
        callbacks[itemId] = child.props?.onSelect;
        itemsJSON.push({
          itemId,
          title: child.props.title,
          groupId: child.props.groupId,
          isChecked: child.props.isChecked,
          isCheckable: child.props.isCheckable,
          icon: child.props.icon,
          type: 'item',
        });
      } else if (child.type === Menu.SubMenu) {
        itemsJSON.push({
          title: child.props.title,
          groupId: child.props.groupId,
          icon: child.props.icon,
          items: convertItemsToJSON(child.props.children, idCounter, callbacks),
          type: 'submenu',
        });
      }
    }
  });

  return itemsJSON;
}
